package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EstudianteDto;
import com.example.demo.exception.WrongIdException;
import com.example.demo.service.EstudianteService;


@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @GetMapping("/{id}")
    public EstudianteDto getById(@PathVariable long id) {
        return estudianteService.findById(id);
    }

    @GetMapping("/all")
    public List<EstudianteDto> getAll() {
        return estudianteService.findAll();
    }

    @PostMapping
    public void create(@RequestBody EstudianteDto e) {
        estudianteService.save(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) throws WrongIdException {
        estudianteService.delete(id);
    }

    @PutMapping("/{id}")
    public EstudianteDto update(@RequestBody EstudianteDto dto, @PathVariable long id) {
        return estudianteService.update(id, dto);
    }

}
