package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Estudiante;

public interface EstudianteRepo extends JpaRepository<Estudiante, Long> {

    //Listar todos los estudiantes
    @Query("SELECT e FROM Estudiante e")
    List<Estudiante> findAllByQuery();

    //Listar todos los estudiantes que tengan un dni mayor a 20M y que su apellido sea “Romero"
    @Query("SELECT e FROM Estudiante e WHERE dni > '20000000' AND LOWER(e.apellido) = lower('Romero')")
    List<Estudiante> findAllWhereDniGreaterThan20000000AndApellidoIsRomero();

    List<Estudiante> findByDniGreaterThanAndApellidoIsIgnoreCase(String dni, String apellido);
}
