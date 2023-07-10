package com.example.demo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CursoDto {
    //private Long id;

    @NotEmpty(message = "El nombre del curso no puede estar en blanco.")
	@Size(min = 3, message = "El nombre del curso debe tener al menos 3 caracteres.")
    private String nombre;

    @NotEmpty(message = "La descripción del curso no puede estar en blanco.")
	@Size(min = 15, message = "La descripción del curso debe tener al menos 15 caracteres.")
    private String descripcion;

    @NotNull(message = "La fecha de inicio es requerida.")
    //@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de fecha inválido. El formato debe ser 'yyyy-MM-dd'")
    private LocalDate inicio;

    @NotNull(message = "La fecha de fin es requerida.")
    //@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de fecha inválido. El formato debe ser 'yyyy-MM-dd'")
    private LocalDate fin;
}
