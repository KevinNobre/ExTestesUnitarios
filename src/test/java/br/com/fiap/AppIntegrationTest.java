package br.com.fiap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve carregar o contexto da aplicação")
    void contextoDeveCarregar() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    @DisplayName("Deve responder 200 na listagem de livros")
    void deveResponderComSucessoNaListagemDeLivros() throws Exception {
        mockMvc.perform(get("/api/livros"))
                .andExpect(status().isOk());
    }
}
