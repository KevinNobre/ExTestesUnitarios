package br.com.fiap.controller;

import br.com.fiap.controller.dto.LivroDTO;
import br.com.fiap.model.Livro;
import br.com.fiap.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody LivroDTO dto) {
        Livro novoLivro = service.cadastrar(dto);
        return new ResponseEntity<>(novoLivro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody LivroDTO dto) {
        Livro livroAtualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(livroAtualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}