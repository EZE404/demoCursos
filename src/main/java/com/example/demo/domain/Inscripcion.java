package com.example.demo.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "curso")
@Getter
@Setter
public class Inscripcion {

    public enum Estado {
        ACEPTADA, RECHAZADA, PENDIENTE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @Column(name = "nombre")
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")    
    private Estado estado;
}
