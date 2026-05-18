package com.corplearn.login.service;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.corplearn.login.dto.LoginRequest;
import com.corplearn.login.dto.LoginResponse;
import com.corplearn.login.dto.RolDto;
import com.corplearn.login.model.Usuario;
import com.corplearn.login.repository.LoginRepository;

import io.jsonwebtoken.Jwts;


@Service
public class LoginService {
    
    @Autowired
    private LoginRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    // Generamos una firma seguraHS256 para el Token JWT
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public LoginResponse autenticar(LoginRequest request) {
        // 1. Validar que el usuario exista
        Usuario usuario = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas o usuario inexistente."));
        
        // 2. Verificar la contraseña encriptada con BCrypt
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas o usuario inexistente");
        }

        // 3. Conexion inter-servicio nativa: Traer el nombre del rol desde rol (Puerto 8090)
        String rolNombre = "UNKNOWN";
        try {
            String urlRoles = "http://localhost:8090/roles/" + usuario.getRolId();
            RolDto rolDB = restTemplate.getForObject(urlRoles, RolDto.class);
            if (rolDB != null) {
                rolNombre = rolDB.getNombre();
            }
        } catch (Exception e) {
        throw   new RuntimeException("Error al consultar el servicio de Roles: " + e.getMessage());
    }

    // 4. Generacion del Token JWT (Expiracion en 2 horas)
    String token = Jwts.builder()
            .subject(usuario.getEmail())
            .claim("userId", usuario.getId())
            .claim("rol", rolNombre)
            .claim("nombreCompleto", usuario.getNombre() + " " + usuario.getApellido())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 7200000))
            .signWith(key)
            .compact();

        return new LoginResponse(token, usuario.getEmail(), rolNombre);
    } 
}
