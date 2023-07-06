package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Curso;
import com.example.demo.domain.EstadoConverter;
import com.example.demo.domain.Estudiante;
import com.example.demo.domain.Inscripcion;
import com.example.demo.exception.WrongIdException;
import com.example.demo.repository.CursoRepo;
import com.example.demo.repository.EstudianteRepo;
import com.example.demo.repository.InscripcionRepo;
import com.example.demo.tools.CollectionsFormatter;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class InscripcionService {
        @Autowired
        InscripcionRepo inscripcionRepo;
        @Autowired
        EstudianteRepo estudianteRepo;
        @Autowired
        CursoRepo cursoRepo;

        public void probarConsultas() {

                System.out.println("inscripcionRepo.findAllWhereEstadoIsPendienteOrRechazada");
                System.out
                                .println(CollectionsFormatter.toStringList(
                                                inscripcionRepo.findAllWhereEstadoIsPendienteOrRechazada()));

                System.out.println(
                                "inscripcionRepo.findByEstadoIsOrEstadoIs(Inscripcion.Estado.PENDIENTE, Inscripcion.Estado.RECHAZADA)");
                System.out.println(CollectionsFormatter.toStringList(
                                inscripcionRepo.findByEstadoIsOrEstadoIs(Inscripcion.Estado.PENDIENTE,
                                                Inscripcion.Estado.RECHAZADA)));

                System.out.println("inscripcionRepo.findAllWhereEstadoIs(Inscripcion.Estado.ACEPTADA)");
                System.out.println(
                                CollectionsFormatter.toStringList(
                                                inscripcionRepo.findAllWhereEstadoIs(Inscripcion.Estado.ACEPTADA)));

                System.out.println("inscripcionRepo.findByEstadoIs(Inscripcion.Estado.PENDIENTE)");
                System.out.println(
                                CollectionsFormatter.toStringList(
                                                inscripcionRepo.findByEstadoIs(Inscripcion.Estado.PENDIENTE)));

                System.out.println(
                                "inscripcionRepo.findAllWhereEstadoIsUsingNative(new EstadoConverter().convertToDatabaseColumn(Inscripcion.Estado.ACEPTADA))");
                System.out.println(CollectionsFormatter.toStringList(inscripcionRepo.findAllWhereEstadoIsUsingNative(
                                new EstadoConverter().convertToDatabaseColumn(Inscripcion.Estado.ACEPTADA))));
        }

        @Transactional
        public void agregarInscripcion(@NotNull @Positive Long curso_id, @NotNull @Positive Long estudiante_id)
                        throws WrongIdException {

                Curso curso = cursoRepo.findById(curso_id).orElseThrow(() -> new WrongIdException("Curso"));
                Estudiante estudiante = estudianteRepo.findById(estudiante_id)
                                .orElseThrow(() -> new WrongIdException("Estudiante"));

                if (estudiante.getEdad() < 18) {
                        throw new RuntimeException("Age not allowed");
                }

                Inscripcion inscripcion = new Inscripcion(null, curso, estudiante, LocalDate.now(),
                                Inscripcion.Estado.PENDIENTE);

                inscripcion = inscripcionRepo.saveAndFlush(inscripcion);

                Objects.requireNonNull(inscripcion.getId(), "No se guardó la inscripción");

                System.out.println(inscripcion);
        }

        public Inscripcion findById(long id) {
                return inscripcionRepo.findById(Long.valueOf(id)).get();
        }

        public List<Inscripcion> findAll() {
                return inscripcionRepo.findAll();
        }
}
