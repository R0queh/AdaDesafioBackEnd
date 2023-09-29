package com.cielo.clientservice.clientsservice.controllers;

import com.cielo.clientservice.clientsservice.entities.PessoaJuridicaBuilder;
import com.cielo.clientservice.clientsservice.entities.clientes.PessoaJuridica;
import com.cielo.clientservice.clientsservice.services.PessoaJuridicaService;
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

@WebMvcTest(PessoaJuridicaController.class)
class PessoaJuridicaControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;
    @MockBean
    private PessoaJuridicaService pessoaJuridicaService;


    @Test
    void buscarPessoaJuridica() throws Exception {
        PessoaJuridica pessoaJuridica = PessoaJuridicaBuilder.builder().build();
        Mockito.when(pessoaJuridicaService.buscarPessoaJuridica(TestConstants.CNPJ)).thenReturn(pessoaJuridica);

        String json = mapperBuilder.build().writeValueAsString(pessoaJuridica);


        final String path = String.format("/company");

        this.mockMvc.perform(MockMvcRequestBuilders.get(path).param("cnpj", TestConstants.CNPJ))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    void deletarPessoaJuridica() throws Exception {
        String expected = "Pessoa jurídica deletada com sucesso";
        Mockito.when(pessoaJuridicaService.deletePessoaJuridica(TestConstants.CNPJ)).thenReturn(expected);


        final String path = String.format("/company");

        this.mockMvc.perform(MockMvcRequestBuilders.delete(path).param("cnpj", TestConstants.CNPJ))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }

    @Test
    void cadastrarPessoaJuridica() throws Exception {
        PessoaJuridica pessoaJuridica = PessoaJuridicaBuilder.builder().build();
        String expected = "Pessoa jurídica cadastrada com sucesso";
        Mockito.when(pessoaJuridicaService.cadastrarPessoaJuridica(pessoaJuridica)).thenReturn(expected);
        String body = mapperBuilder.build().writeValueAsString(pessoaJuridica);

        final String path = String.format("/company");

        this.mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void atualizarPessoaJuridica() throws Exception {
        PessoaJuridica pessoaFisica = PessoaJuridicaBuilder.builder().build();
        Mockito.when(pessoaJuridicaService.atualizarPessoaJuridica(pessoaFisica)).thenReturn(pessoaFisica);
        String body = mapperBuilder.build().writeValueAsString(pessoaFisica);

        final String path = String.format("/company");

        this.mockMvc.perform(MockMvcRequestBuilders.patch(path)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(body));
    }
}