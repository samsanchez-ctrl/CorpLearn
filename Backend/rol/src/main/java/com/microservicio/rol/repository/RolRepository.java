package com.microservicio.rol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservicio.rol.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}