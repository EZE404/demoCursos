package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.domain.Estudiante;
import com.example.demo.service.EstudianteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @GetMapping
    public Estudiante getById(long id) {
        return estudianteService.findById(id);
    }

    @GetMapping("/all")
    public List<Estudiante> getAll() {
        return estudianteService.findAll();
    }

    @PostMapping
    public void create(@RequestBody Estudiante e) {
        estudianteService.save(e);
    }

}
