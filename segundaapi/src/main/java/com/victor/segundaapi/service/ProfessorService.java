package com.victor.segundaapi.service;

import com.victor.segundaapi.model.ProfessorModel;
import com.victor.segundaapi.repository.ProfessorRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public List<ProfessorModel> listarProfessores() {
        return professorRepository.findAll();
    }

    public ProfessorModel cadastrarProfessor(ProfessorModel professorModel) {
        boolean professorExiste = professorRepository.existsByEmail(professorModel.getEmail());

        if (professorExiste || professorModel.getSenha() == null || professorModel.getSenha().isEmpty()) {
            return null;
        }

        String senhaCriptografada = BCrypt.hashpw(professorModel.getSenha(), BCrypt.gensalt());
        professorModel.setSenha(senhaCriptografada);

        return professorRepository.save(professorModel);
    }

    public boolean autenticar(String email, String senha) {
        // TODO: Lógica de autenticação a ser feita depois
        return false;
    }

    public Optional<ProfessorModel> buscarProfessor(Long id) {
        return professorRepository.findById(id);
    }

    public boolean deletarProfessor(Long id) {
        boolean professorExiste = professorRepository.existsById(id);
        if (professorExiste) {
            professorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}