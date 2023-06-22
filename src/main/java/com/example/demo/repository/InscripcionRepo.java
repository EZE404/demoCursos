package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Inscripcion;

public interface InscripcionRepo extends JpaRepository<Inscripcion, Long>{
    
}
