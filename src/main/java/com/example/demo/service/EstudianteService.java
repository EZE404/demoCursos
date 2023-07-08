package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Estudiante;
import com.example.demo.dto.EstudianteDto;
import com.example.demo.repository.EstudianteRepo;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepo estudianteRepo;

    public EstudianteDto findById(long id) {
        return estudianteRepo.findById(Long.valueOf(id)).get().convertToDto();
    }

    public List<EstudianteDto> findAll() {
        return estudianteRepo.findAll().stream().map(Estudiante::convertToDto).toList();
    }

    public void save(EstudianteDto e) {
        Estudiante estudiante = new Estudiante(null, e.getNombre(), e.getApellido(), e.getDni(), e.getEmail(), e.getNacimiento());
        estudianteRepo.save(estudiante);
    }

    public void delete(long id) {
        estudianteRepo.deleteById(Long.valueOf(id));
    }
}
