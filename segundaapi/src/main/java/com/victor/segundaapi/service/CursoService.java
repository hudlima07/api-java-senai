package com.victor.segundaapi.service;

import com.victor.segundaapi.model.CursoModel;
import com.victor.segundaapi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<CursoModel> listarCursos() {
        return cursoRepository.findAll();
    }

    public CursoModel cadastrarCurso(CursoModel cursoModel) {
        return cursoRepository.save(cursoModel);
    }

    public Optional<CursoModel> buscarCurso(Long id) {
        return cursoRepository.findById(id);
    }

    public boolean deletarCurso(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}