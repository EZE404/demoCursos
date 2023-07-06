package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Estudiante;
import com.example.demo.repository.EstudianteRepo;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepo estudianteRepo;

    public Estudiante findById(long id) {
        return estudianteRepo.findById(Long.valueOf(id)).get();
    }

    public List<Estudiante> findAll() {
        return estudianteRepo.findAll();
    }

    public void save(Estudiante e) {
        estudianteRepo.save(e);
    }
}
