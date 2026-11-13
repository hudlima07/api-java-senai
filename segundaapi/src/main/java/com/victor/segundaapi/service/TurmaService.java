package com.victor.segundaapi.service;

import com.victor.segundaapi.model.TurmaModel;
import com.victor.segundaapi.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository turmaRepository;

    public List<TurmaModel> listarTurmas() {
        return turmaRepository.findAll();
    }

    public TurmaModel cadastrarTurma(TurmaModel turmaModel) {
        return turmaRepository.save(turmaModel);
    }

    public Optional<TurmaModel> buscarTurma(Long id) {
        return turmaRepository.findById(id);
    }

    public boolean deletarTurma(Long id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}