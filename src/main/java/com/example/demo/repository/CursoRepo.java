package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Curso;

public interface CursoRepo extends JpaRepository<Curso, Long> {

    // Listar todos los cursos
    @Query("SELECT c FROM Curso c")
    List<Curso> findAllByQuery();

    // Listar todos los cursos que hayan empezado después de “01/02/2020”
    @Query("SELECT c FROM Curso c WHERE c.inicio > TO_DATE('01/02/2020','dd/mm/YYYY')")
    List<Curso> findAllWhereInicioIsAfter01022020();

    List<Curso> findByInicioGreaterThan(LocalDate fecha);


}
