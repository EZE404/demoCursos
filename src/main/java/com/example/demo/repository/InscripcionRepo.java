package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Inscripcion;


public interface InscripcionRepo extends JpaRepository<Inscripcion, Long>{
    
    // Listar todas las inscripciones rechazadas o pendiente
    @Query("SELECT i FROM Inscripcion i WHERE i.estado = 'PENDIENTE' OR i.estado = 'RECHAZADA'")
    List<Inscripcion> findAllWhereEstadoIsPendienteOrRechazada();

    List<Inscripcion> findByEstadoIsOrEstadoIs(Inscripcion.Estado estado1, Inscripcion.Estado estado2);

    // Listar todas las inscripciones en base a un parámetro de estado
    @Query("SELECT i FROM Inscripcion i WHERE i.estado = :estado")
    List<Inscripcion> findAllWhereEstadoIs(@Param("estado") Inscripcion.Estado estado);

    List<Inscripcion> findByEstadoIs(Inscripcion.Estado estado);

    // Listar todas las inscripciones en base a un parámetro de estado utilizando consulta nativa
    @Query(value = "SELECT i.* FROM inscripcion i WHERE i.estado = :estado", nativeQuery = true)
    List<Inscripcion> findAllWhereEstadoIsUsingNative(Inscripcion.Estado estado);
}
