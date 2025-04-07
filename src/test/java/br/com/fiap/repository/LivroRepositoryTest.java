package br.com.fiap.repository;

import br.com.fiap.model.Livro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LivroRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private LivroRepository livroRepository;

    @Test
    @DisplayName("Deve salvar um livro no banco de dados")
    void deveSalvarLivro() {
        Livro livro = new Livro();
        livro.setTitulo("Dom Casmurro");
        livro.setAutor("Machado de Assis");
        livro.setIsbn("ABC123");
        livro.setPreco(BigDecimal.valueOf(49.90));

        Livro salvo = livroRepository.save(livro);

        assertNotNull(salvo.getId());
        assertEquals("Dom Casmurro", salvo.getTitulo());
    }

    @Test
    @DisplayName("Deve buscar um livro por ID")
    void deveBuscarLivroPorId() {
        Livro livro = new Livro();
        livro.setTitulo("1984");
        livro.setAutor("George Orwell");
        livro.setIsbn("XYZ789");
        livro.setPreco(BigDecimal.valueOf(59.90));

        Livro salvo = livroRepository.save(livro);

        Optional<Livro> resultado = livroRepository.findById(salvo.getId());

        assertTrue(resultado.isPresent());
        assertEquals("1984", resultado.get().getTitulo());
    }

    @Test
    @DisplayName("Deve deletar um livro")
    void deveDeletarLivro() {
        Livro livro = new Livro();
        livro.setTitulo("A Revolução dos Bichos");
        livro.setAutor("George Orwell");
        livro.setIsbn("REV123");
        livro.setPreco(BigDecimal.valueOf(39.90));

        Livro salvo = livroRepository.save(livro);
        livroRepository.deleteById(salvo.getId());

        Optional<Livro> resultado = livroRepository.findById(salvo.getId());

        assertFalse(resultado.isPresent());
    }









}
