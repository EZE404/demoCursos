package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.domain.Inscripcion.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InscripcionDto {
    private Long id;

    private Long curso_id;

    private Long estudiante_id;

    private LocalDate fecha;

    private Estado estado;
}
