package com.victor.segundaapi.controller;

import com.victor.segundaapi.model.TurmaModel;
import com.victor.segundaapi.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/turma")
@CrossOrigin(value = "*")
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<TurmaModel>> listarTurmas() {
        return ResponseEntity.ok().body(turmaService.listarTurmas());
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<TurmaModel> cadastrarTurma(@RequestBody TurmaModel turma) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.cadastrarTurma(turma));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
        if (turmaService.deletarTurma(id)) {
            return ResponseEntity.ok().body(Map.of("message", "Turma deletada com sucesso"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Turma nao existe"));
    }
}