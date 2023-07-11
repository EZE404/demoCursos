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
import com.example.demo.dto.InscripcionDto;
import com.example.demo.exception.AgeNotAllowedException;
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

    @Transactional
    public void save(@NotNull @Positive Long curso_id, @NotNull @Positive Long estudiante_id)
            throws WrongIdException, AgeNotAllowedException {

        Curso curso = cursoRepo.findById(curso_id).orElseThrow(() -> new WrongIdException("Curso"));
        Estudiante estudiante = estudianteRepo.findById(estudiante_id)
                .orElseThrow(() -> new WrongIdException("Estudiante"));

        if (estudiante.getEdad() < 18) {
            throw new AgeNotAllowedException();
        }

        Inscripcion inscripcion = new Inscripcion(null, curso, estudiante, LocalDate.now(),
                Inscripcion.Estado.PENDIENTE);

        inscripcion = inscripcionRepo.saveAndFlush(inscripcion);

        Objects.requireNonNull(inscripcion.getId(), "No se guardó la inscripción");

        System.out.println(inscripcion);
    }

    public InscripcionDto findById(long id) {
        return inscripcionRepo.findById(Long.valueOf(id)).get().convertToDto();
    }

    public List<InscripcionDto> findAll() {
        return inscripcionRepo.findAll().stream().map(Inscripcion::convertToDto).toList();
    }

    public void delete(long id) throws WrongIdException {
        if (!inscripcionRepo.existsById(Long.valueOf(id)))
            throw new WrongIdException("Inscripción");
        inscripcionRepo.deleteById(Long.valueOf(id));
    }
}
