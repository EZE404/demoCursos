package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Curso;
import com.example.demo.service.CursoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public Curso getById(long id) {
        return cursoService.findById(id);
    }

    @GetMapping("/all")
    public List<Curso> getAll() {
        return cursoService.findAll();
    }

    @PostMapping
    public void create(@RequestBody Curso e) {
        cursoService.save(e);
    }
}
