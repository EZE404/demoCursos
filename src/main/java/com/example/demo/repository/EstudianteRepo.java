package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Estudiante;

public interface EstudianteRepo extends JpaRepository<Estudiante, Long> {
    
}
