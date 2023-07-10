package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.InscripcionDto;
import com.example.demo.exception.AgeNotAllowedException;
import com.example.demo.exception.WrongIdException;
import com.example.demo.service.InscripcionService;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping("/{id}")
    public InscripcionDto getById(@PathVariable long id) {
        return inscripcionService.findById(id);
    }

    @GetMapping("/all")
    public List<InscripcionDto> getAll() {
        return inscripcionService.findAll();
    }

    @PostMapping
    public void create(@RequestBody InscripcionDto e) throws WrongIdException, AgeNotAllowedException {
        inscripcionService.save(e.getCurso_id(), e.getEstudiante_id());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) throws WrongIdException {
        inscripcionService.delete(id);
    }
}
