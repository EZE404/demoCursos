package com.example.demo.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoConverter implements AttributeConverter<Inscripcion.Estado, String> {

    @Override
    public String convertToDatabaseColumn(Inscripcion.Estado estado) {
        return estado.toString(); // Convert Enum to string
    }

    @Override
    public Inscripcion.Estado convertToEntityAttribute(String dbData) {
        return Inscripcion.Estado.valueOf(dbData); // Convert string to Enum
    }
}
