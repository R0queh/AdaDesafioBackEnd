package com.cielo.clientservice.clientsservice.services;

import com.cielo.clientservice.clientsservice.DTO.ClienteDTO;
import com.cielo.clientservice.clientsservice.entities.PessoaFisicaBuilder;
import com.cielo.clientservice.clientsservice.entities.PessoaJuridicaBuilder;
import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaFisica;
import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaJuridica;
import com.cielo.clientservice.clientsservice.mappers.ClienteDTOMapper;
import com.cielo.clientservice.clientsservice.mappers.ClienteDTOMapperImpl;
import com.cielo.clientservice.clientsservice.repositories.v1.PessoaFisicaRepository;
import com.cielo.clientservice.clientsservice.repositories.v1.PessoaJuridicaRepository;
import com.cielo.clientservice.clientsservice.services.v1.FilaDeAtendimentoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class FilaDeAtendimentoServiceTest {

    @Mock
    PessoaJuridicaRepository pessoaJuridicaRepository;
    @Mock
    PessoaFisicaRepository pessoaFisicaRepository;

    @Mock
    ClienteDTOMapper clienteDTOMapper;
    @InjectMocks
    FilaDeAtendimentoService filaDeAtendimentoService;

    @Test
    void buscarProximoDaFilaDeAtendimento_deveRetornarProximoDaFila_quandoFilaDeClientesEstiverPopulada(){
        ClienteDTOMapper mapper = new ClienteDTOMapperImpl();
        PessoaJuridica pessoaJuridica = PessoaJuridicaBuilder.builder().build();
        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder().build();
        ClienteDTO expected =mapper.toClienteDTO(pessoaJuridica);
        Mockito.when(pessoaFisicaRepository.findAll()).thenReturn(List.of(pessoaFisica));
        Mockito.when(pessoaJuridicaRepository.findAll()).thenReturn(List.of(pessoaJuridica));
        Mockito.when(clienteDTOMapper.toClienteDTO(Mockito.any(PessoaJuridica.class))).thenReturn(mapper.toClienteDTO(pessoaJuridica));
        Mockito.when(clienteDTOMapper.toClienteDTO(Mockito.any(PessoaFisica.class))).thenReturn(mapper.toClienteDTO(pessoaFisica));

        ClienteDTO result = filaDeAtendimentoService.buscarProximoDaFilaDeAtendimento();
        Assertions.assertEquals(expected.getCnpj(), result.getCnpj());
        Assertions.assertEquals(expected.getCpf(), result.getCpf());
        Assertions.assertEquals(expected.getEmail(), result.getEmail());
    }
}