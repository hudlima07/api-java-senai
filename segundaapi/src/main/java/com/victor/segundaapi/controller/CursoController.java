package com.victor.segundaapi.controller;

import com.victor.segundaapi.model.CursoModel;
import com.victor.segundaapi.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/curso")
@CrossOrigin(value = "*")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<CursoModel>> listarCursos() {
        return ResponseEntity.ok().body(cursoService.listarCursos());
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<CursoModel> cadastrarCurso(@RequestBody CursoModel curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.cadastrarCurso(curso));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
        if (cursoService.deletarCurso(id)) {
            return ResponseEntity.ok().body(Map.of("message", "Curso deletado com sucesso"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Curso nao existe"));
    }
}