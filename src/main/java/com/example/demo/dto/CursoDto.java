package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CursoDto {
    private Long id;

    @NotEmpty(message = "El nombre del curso no puede estar en blanco.")
	@Size(min = 3, message = "El nombre del curso debe tener al menos 3 caracteres.")
    private String nombre;

    @NotEmpty(message = "La descripción del curso no puede estar en blanco.")
	@Size(min = 15, message = "La descripción del curso debe tener al menos 15 caracteres.")
    private String descripcion;

    @NotEmpty
    private LocalDate inicio;

    @NotEmpty
    private LocalDate fin;
}
