package com.microservicio.modulo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicio.modulo.dto.ProgramaDto;
import com.microservicio.modulo.model.Modulo;
import com.microservicio.modulo.repository.ModuloRepository;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Modulo> listar(){
        return repository.findAll();
    }

    public Modulo guardar(Modulo modulo){
        try {
        // Validar que el Programa exista
        ProgramaDto programa = restTemplate.getForObject(
            "http://localhost:8088/programas/" + modulo.getProgramaId(), ProgramaDto.class);

        if (programa != null) {
            return repository.save(modulo);
        } else {
            throw new RuntimeException("Error: El Programa especificado no existe.");
        }
    } catch (Exception e) {
        throw new RuntimeException("Error de conexión: El microservicio de Programa no responde.");
    }
    }

    public Modulo buscar(Long id){
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }


}
