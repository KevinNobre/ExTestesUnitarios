package br.com.fiap.service;

import br.com.fiap.controller.dto.LivroDTO;
import br.com.fiap.model.Livro;
import br.com.fiap.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LivroServiceTest {

    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @BeforeEach
    void InitMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarLivroExistenteComSucesso() {
        LivroDTO dadosAtualizacao = new LivroDTO ();
        dadosAtualizacao.setTitulo("Novo");
        dadosAtualizacao.setAutor("AutorAtualizado");
        dadosAtualizacao.setIsbn("4321");
        dadosAtualizacao.setPreco(BigDecimal.ONE);

        Livro livroExistente = new Livro();
        livroExistente.setId(1L);

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livroExistente));
        when(livroRepository.save(any(Livro.class))).thenReturn(livroExistente);

        Livro livroAtualizado = livroService.atualizar(1L, dadosAtualizacao);

        assertEquals("Novo", livroAtualizado.getTitulo());
    }

    @Test
    void deveCadastrarLivroCorretamente() {
        LivroDTO novoLivroDTO = new LivroDTO();
        novoLivroDTO.setTitulo("Título");
        novoLivroDTO.setAutor("Autor");
        novoLivroDTO.setIsbn("1234");
        novoLivroDTO.setPreco(BigDecimal.TEN);

        Livro livroPersistido = new Livro();
        livroPersistido.setId(1L);

        when(livroRepository.save(any(Livro.class))).thenReturn(livroPersistido);

        Livro resultadoCadastro = livroService.cadastrar(novoLivroDTO);

        assertNotNull(resultadoCadastro.getId());
        verify(livroRepository).save(any(Livro.class));
    }

    @Test
    void deveLancarErroQuandoLivroNaoForEncontradoParaAtualizacao() {
        LivroDTO dadosInexistente = new LivroDTO();
        dadosInexistente.setTitulo("Título");
        dadosInexistente.setAutor("Autor");
        dadosInexistente.setIsbn("1234");
        dadosInexistente.setPreco(BigDecimal.TEN);

        when(livroRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () ->
                livroService.atualizar(1L, dadosInexistente)
        );

        assertEquals("Livro não encontrado", excecao.getMessage());
    }











}
