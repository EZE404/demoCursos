package com.example.demo.domain;

import java.time.LocalDate;
import java.time.Period;

import com.example.demo.dto.EstudianteDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estudiante")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni")
    private String dni;
    @Column(name = "email")
    private String email;

    @Column(name = "nacimiento")
    private LocalDate nacimiento;

    public int getEdad() {
        return Period.between(nacimiento, LocalDate.now()).getYears();
    }

    public EstudianteDto convertToDto() {
        return new EstudianteDto(this.nombre, this.apellido, this.dni, this.email, this.nacimiento);
    }
}
