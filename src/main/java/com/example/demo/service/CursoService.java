package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Curso;
import com.example.demo.repository.CursoRepo;

@Service
public class CursoService {
    @Autowired
    private CursoRepo cursoRepo;

    public Curso findById(long id) {
        return cursoRepo.findById(Long.valueOf(id)).get();
    }

    public List<Curso> findAll() {
        return cursoRepo.findAll();
    }

    public void save(Curso e) {
        cursoRepo.save(e);
    }
}
