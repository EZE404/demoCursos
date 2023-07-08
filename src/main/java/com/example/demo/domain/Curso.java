package com.example.demo.domain;

import java.time.LocalDate;

import com.example.demo.dto.CursoDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "inicio")
    private LocalDate inicio;

    @Column(name = "fin")
    private LocalDate fin;

    @Override
    public String toString() {
        return "Curso [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", inicio=" + inicio
                + ", fin=" + fin + "]";
    }

    public CursoDto convertToDto() {
        CursoDto cursoDto = new CursoDto(this.id, this.nombre, this.descripcion, this.inicio, this.fin);

        return cursoDto;
    }
}
