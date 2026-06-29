package com.corplearn.rol.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corplearn.rol.dto.RolDto;
import com.corplearn.rol.model.Rol;
import com.corplearn.rol.service.RolService;

@RestController
@RequestMapping("/roles")
public class RolController extends BaseController {
    
    @Autowired
    private RolService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTodos() {
        try {
            List<Rol> roles = service.obtenerTodos();
            return createResponse("Roles obtenidos exitosamente", roles, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al listar los roles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Long id) {
        try {
            Rol rol = service.obtenerPorId(id);
            return createResponse("Rol encontrado con éxito", rol, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("No se pudo encontrar el rol: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody RolDto dto) {
        try {
            Rol nuevoRol = service.crear(dto);
            return createResponse("Rol creado correctamente", nuevoRol, HttpStatus.CREATED);
        } catch (Exception e) {
            return createErrorResponse("Error al crear el rol: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> modificar(@PathVariable Long id, @RequestBody RolDto dto) {
        try {
            Rol rolActualizado = service.actualizar(id, dto);
            return createResponse("Rol modificado correctamente", rolActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Error al actualizar el rol: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> borrar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return createResponse("Rol eliminado correctamente con ID: " + id, null, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("No se pudo eliminar el rol: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
