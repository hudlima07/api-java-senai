package com.victor.segundaapi.service;

import com.victor.segundaapi.model.AlunoModel;
import com.victor.segundaapi.repository.AlunoRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public List<AlunoModel> listarAlunos() {
        return alunoRepository.findAll();
    }

    public AlunoModel cadastrarAluno(AlunoModel alunoModel) {
        boolean alunoExiste = alunoRepository.existsByEmail(alunoModel.getEmail());

        if (alunoExiste || alunoModel.getSenha() == null || alunoModel.getSenha().isEmpty()) {
            return null;
        }

        String senhaCriptografada = BCrypt.hashpw(alunoModel.getSenha(), BCrypt.gensalt());
        alunoModel.setSenha(senhaCriptografada);

        return alunoRepository.save(alunoModel);
    }

    public boolean autenticar(String email, String senha) {
        Optional<AlunoModel> alunoOpt = alunoRepository.findByEmail(email);
        if (alunoOpt.isPresent()) {
            return BCrypt.checkpw(senha, alunoOpt.get().getSenha());
        }
        return false;
    }

    public Optional<AlunoModel> buscarAluno(Long id) {
        return alunoRepository.findById(id);
    }

    public boolean deletarAluno(Long id) {
        boolean alunoExiste = alunoRepository.existsById(id);
        if (alunoExiste) {
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}