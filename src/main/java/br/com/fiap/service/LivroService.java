package br.com.fiap.service;

import br.com.fiap.controller.dto.LivroDTO;
import br.com.fiap.model.Livro;
import br.com.fiap.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Livro cadastrar(LivroDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        livro.setPreco(dto.getPreco());
        return repository.save(livro);
    }

    public Livro atualizar(Long id, LivroDTO dto) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        livro.setPreco(dto.getPreco());
        return repository.save(livro);
    }

    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public List<Livro> buscarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}