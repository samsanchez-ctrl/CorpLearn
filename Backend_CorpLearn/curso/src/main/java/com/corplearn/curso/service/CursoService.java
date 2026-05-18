package com.corplearn.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corplearn.curso.model.Curso;
import com.corplearn.curso.repository.CursoRepository;

@Service
public class CursoService {
    
    @Autowired
    private CursoRepository repository;

    public List<Curso> obtenerTodos() {
        return repository.findAll();
    }

    public Curso obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con el ID: " + id));
    }

    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    public Curso actualizar(Long id, Curso cursoDetalles) {
        // Buscamos el curso existente; si no existe, lanzamos excepcion
        Curso cursoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede modificar. Curso no encontrado con el ID: " + id));

        // Modificamos los campos con la nueva informacion que viene del Front
        cursoExistente.setTitulo(cursoDetalles.getTitulo());
        cursoExistente.setDescripcion(cursoDetalles.getDescripcion());
        cursoExistente.setDuracion(cursoDetalles.getDuracion());
        cursoExistente.setImagenUrl(cursoDetalles.getImagenUrl());

        // Guardamos los cambios en la base de datos
        return repository.save(cursoExistente);
    }
    
    public void eliminar(Long id) {
        // Verificamos si existe antes de intentar borrarlo para evitar errores de JPA
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Curso no encontrado con ID: " + id));

        repository.delete(curso);
    }
}
