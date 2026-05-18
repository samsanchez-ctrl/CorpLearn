package com.corplearn.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corplearn.login.model.Usuario;

@Repository
public interface LoginRepository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
