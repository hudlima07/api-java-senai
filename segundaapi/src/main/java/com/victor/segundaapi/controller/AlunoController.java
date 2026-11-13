package com.victor.segundaapi.controller;

import com.victor.segundaapi.model.AlunoModel;
import com.victor.segundaapi.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

    @RestController
    @RequestMapping(value = "/aluno")
    @CrossOrigin(value = "*")
    public class AlunoController {

        @Autowired
        AlunoService alunoService;

        @PostMapping(value = "/login")
        public ResponseEntity<?> login(@RequestBody AlunoModel aluno) {
            boolean sucesso = alunoService.autenticar(aluno.getEmail(), aluno.getSenha());
            if (sucesso) {
                return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Login efetuado com sucesso"));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Email ou senha invalida"));
        }

        @GetMapping(value = "/listar")
        public ResponseEntity<List<AlunoModel>> listarAlunos() {
            return ResponseEntity.ok().body(alunoService.listarAlunos());
        }

        @PostMapping(value = "/cadastrar")
        public ResponseEntity<?> cadastrarAluno(@RequestBody AlunoModel aluno) {
            AlunoModel alunoSalvo = alunoService.cadastrarAluno(aluno);
            if (alunoSalvo == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensagem", "Email ou senha ja cadastrado ou errado"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
        }

        @DeleteMapping(value = "/{id}")
        public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
            if (alunoService.deletarAluno(id)) {
                return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Aluno deletado com sucesso"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Aluno não existe"));
            }
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
            Optional<AlunoModel> aluno = alunoService.buscarAluno(id);
            if (aluno.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(aluno.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Aluno nao existe"));
            }
        }
    }

