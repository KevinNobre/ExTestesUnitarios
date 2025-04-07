package br.com.fiap.repository;

import br.com.fiap.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIsbn(String isbn);
    List<Livro> findByAutorContaining(String autor);
    List<Livro> findByTituloContaining(String titulo);
}