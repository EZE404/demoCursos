package com.example.demo.domain;

import java.time.LocalDate;

import com.example.demo.dto.InscripcionDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inscripcion")
@SqlResultSetMapping(name = "InscripcionMapping", entities = @EntityResult(entityClass = Inscripcion.class))
@NamedNativeQuery(name = "Inscripcion.findAllWhereEstadoIsUsingNative", query = "SELECT i.* FROM inscripcion i WHERE i.estado = :estado", resultClass = Inscripcion.class, resultSetMapping = "InscripcionMapping")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "fecha")
    private LocalDate fecha;

    //@Enumerated(EnumType.STRING) // Deshabilitado para usar converter personalizado
    @Column(name = "estado")
    //@Convert(converter = EstadoConverter.class) // Deshabilitado porque EstadoConverter está con autoApply
    private Estado estado;

    public InscripcionDto convertToDto() {
        return new InscripcionDto(this.id, this.curso.getId(), this.estudiante.getId(), this.fecha, this.estado);
    }

}
