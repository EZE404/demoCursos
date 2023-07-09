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

import com.example.demo.dto.CursoDto;
import com.example.demo.service.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping("/{id}")
    public CursoDto getById(@PathVariable long id) {
        return cursoService.findById(id);
    }

    @GetMapping("/all")
    public List<CursoDto> getAll() {
        return cursoService.findAll();
    }

    @PostMapping
    public void create(@RequestBody CursoDto e) {
        cursoService.save(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        cursoService.delete(id);
    }

    @PutMapping("/{id}")
    public CursoDto update(@RequestBody CursoDto dto, @PathVariable long id) {
        return cursoService.update(id, dto);
    }
}
