package com.example.demo.domain;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "nacimiento")
    private LocalDate nacimiento;

    public int getEdad() {
        return Period.between(nacimiento, LocalDate.now()).getYears();
    }
}
