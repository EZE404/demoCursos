package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Curso;
import com.example.demo.dto.CursoDto;
import com.example.demo.exception.WrongIdException;
import com.example.demo.repository.CursoRepo;

@Service
public class CursoService {
    @Autowired
    private CursoRepo cursoRepo;

    public CursoDto findById(long id) {
        return cursoRepo.findById(Long.valueOf(id)).get().convertToDto();
    }

    public List<CursoDto> findAll() {
        return cursoRepo.findAll().stream().map(Curso::convertToDto).toList();
    }

    public void save(CursoDto e) {
        Curso curso = new Curso(null, e.getNombre(), e.getDescripcion(), e.getInicio(), e.getFin());
        cursoRepo.save(curso);
    }

    public void delete(long id) throws WrongIdException {
        if (!cursoRepo.existsById(Long.valueOf(id)))
            throw new WrongIdException("Curso");
            
        cursoRepo.deleteById(Long.valueOf(id));
    }

    public CursoDto update(long id, CursoDto dto) {
        Curso curso = new Curso(Long.valueOf(id), dto.getNombre(), dto.getDescripcion(), dto.getInicio(), dto.getFin());
        return cursoRepo.saveAndFlush(curso).convertToDto();
    }
}
