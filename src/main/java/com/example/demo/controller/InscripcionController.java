package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Inscripcion;
import com.example.demo.service.InscripcionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public Inscripcion getById(long id) {
        return inscripcionService.findById(id);
    }

    @GetMapping("/all")
    public List<Inscripcion> getAll() {
        return inscripcionService.findAll();
    }

/*     @PostMapping
    public void create(@RequestBody Inscripcion e) {
        inscripcionService.save(e);
    } */
}
