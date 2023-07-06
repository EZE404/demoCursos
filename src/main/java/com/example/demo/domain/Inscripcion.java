package com.example.demo.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inscripcion")
@SqlResultSetMapping(name = "InscripcionMapping", entities = @EntityResult(entityClass = Inscripcion.class))
@NamedNativeQuery(name = "Inscripcion.findAllWhereEstadoIsUsingNative", query = "SELECT i.* FROM inscripcion i WHERE i.estado = :estado", resultClass = Inscripcion.class, resultSetMapping = "InscripcionMapping")
@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Override
    public String toString() {
        return "Inscripcion[id=" + id + ", curso=" + curso + ", estudiante=" + estudiante + ", fecha=" + fecha
                + ", estado=" + estado + "]";
    }

}
