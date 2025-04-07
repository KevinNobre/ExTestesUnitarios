package br.com.fiap.controller.steps;

import br.com.fiap.controller.dto.LivroDTO;
import br.com.fiap.model.Livro;
import br.com.fiap.repository.LivroRepository;
import br.com.fiap.service.LivroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LivroRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void limparBase() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve cadastrar um livro com sucesso")
    void deveCadastrarLivro() throws Exception {
        LivroDTO dto = new LivroDTO();
        dto.setTitulo("Livro de Teste");
        dto.setAutor("Autor Exemplo");
        dto.setIsbn("ABCD1234");
        dto.setPreco(BigDecimal.valueOf(49.90));

        mockMvc.perform(post("/api/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("Livro de Teste"))
                .andExpect(jsonPath("$.autor").value("Autor Exemplo"))
                .andExpect(jsonPath("$.isbn").value("ABCD1234"));
    }

    @Test
    @DisplayName("Deve listar todos os livros cadastrados")
    void deveListarTodosLivros() throws Exception {
        Livro livro = new Livro();
        livro.setTitulo("Livro Integrado");
        livro.setAutor("Autor 1");
        livro.setIsbn("INT123");
        livro.setPreco(BigDecimal.TEN);
        repository.save(livro);

        mockMvc.perform(get("/api/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Livro Integrado"))
                .andExpect(jsonPath("$[0].autor").value("Autor 1"))
                .andExpect(jsonPath("$[0].isbn").value("INT123"));
    }
}