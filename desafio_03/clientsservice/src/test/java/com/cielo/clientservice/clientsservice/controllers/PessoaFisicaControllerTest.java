package com.cielo.clientservice.clientsservice.controllers;

import com.cielo.clientservice.clientsservice.controllers.v1.PessoaFisicaController;
import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaFisica;
import com.cielo.clientservice.clientsservice.services.v1.PessoaFisicaService;
import com.cielo.clientservice.clientsservice.entities.v1.PessoaFisicaBuilder;
import com.cielo.clientservice.clientsservice.utils.TestConstants;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PessoaFisicaController.class)
class PessoaFisicaControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;
    @MockBean private PessoaFisicaService pessoaFisicaService;


    @Test
    void buscarPessoaFisica() throws Exception {
        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder().build();
        Mockito.when(pessoaFisicaService.buscarPessoaFisica(TestConstants.CPF)).thenReturn(pessoaFisica);

        String json = mapperBuilder.build().writeValueAsString(pessoaFisica);


        final String path = String.format("/person/%s", TestConstants.CPF);

        this.mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    void deletarPessoaFisica() throws Exception {
        String expected = "Pessoa física deletada com sucesso";
        Mockito.when(pessoaFisicaService.deletePessoaFisica(TestConstants.CPF)).thenReturn(expected);


        final String path = String.format("/person/%s", TestConstants.CPF);

        this.mockMvc.perform(MockMvcRequestBuilders.delete(path))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }

    @Test
    void cadastroPessoaFisica() throws Exception {
        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder().build();
        String expected = "Pessoa física cadastrada com sucesso";
        Mockito.when(pessoaFisicaService.cadastrarPessoaFisica(pessoaFisica)).thenReturn(expected);
        String body = mapperBuilder.build().writeValueAsString(pessoaFisica);

        final String path = String.format("/person");

        this.mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void atualizarPessoaFisica() throws Exception {
        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder().build();
        Mockito.when(pessoaFisicaService.atualizarPessoaFisica(pessoaFisica)).thenReturn(pessoaFisica);
        String body = mapperBuilder.build().writeValueAsString(pessoaFisica);

        final String path = String.format("/person");

        this.mockMvc.perform(MockMvcRequestBuilders.patch(path)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(body));
    }
}