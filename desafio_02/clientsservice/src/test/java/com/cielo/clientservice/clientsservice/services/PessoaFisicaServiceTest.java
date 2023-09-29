package com.cielo.clientservice.clientsservice.services;

import com.cielo.clientservice.clientsservice.entities.clientes.PessoaFisica;
import com.cielo.clientservice.clientsservice.entities.PessoaFisicaBuilder;
import com.cielo.clientservice.clientsservice.exceptions.ClientServiceException;
import com.cielo.clientservice.clientsservice.repositories.PessoaFisicaRepository;
import com.cielo.clientservice.clientsservice.utils.TestConstants;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PessoaFisicaServiceTest {

    @Mock
    PessoaFisicaRepository pessoaFisicaRepository;
    @InjectMocks
    PessoaFisicaService pessoaFisicaService;

    @Test
    void buscarPessoaJuridica_deveRetornarPessoaJuridica_quandoReceberUmCNPJValido() {
        PessoaFisica expected = PessoaFisicaBuilder.builder().build();
        Mockito.when(pessoaFisicaRepository.getReferenceById(TestConstants.CNPJ)).thenReturn(PessoaFisicaBuilder.builder().build());
        PessoaFisica result = pessoaFisicaService.buscarPessoaFisica(TestConstants.CNPJ);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void buscarPessoaFisica_deveRetornarNaoAchado_quandoReceberUmCNPJNaoCadastrado() {
        Mockito.when(pessoaFisicaRepository.getReferenceById(TestConstants.CNPJ)).thenThrow(EntityNotFoundException.class);
        final Exception result = Assertions.assertThrows(ClientServiceException.class, () -> pessoaFisicaService.buscarPessoaFisica(TestConstants.CNPJ));
        String expected = "Pessoa física não encontrada(o)";
        Assertions.assertEquals(expected, result.getMessage());
    }

    @Test
    void deletePessoaFisica_deveChamarRepositoryUmaVez_quandoReceberUmCNPJValido() {
        pessoaFisicaService.deletePessoaFisica(TestConstants.CNPJ);
        Mockito.verify(pessoaFisicaRepository, Mockito.times(1)).deleteById(TestConstants.CNPJ);
    }

    @Test
    void cadastrarPessoaFisica_deveRetornarMensagemDeSucesso_quandoReceberUmaPessoaFisicaValida() {
        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder().build();
        Mockito.when(pessoaFisicaRepository.getReferenceById(pessoaFisica.getCpf())).thenThrow(EntityNotFoundException.class);
        String result = pessoaFisicaService.cadastrarPessoaFisica(pessoaFisica);
        Assertions.assertEquals("Pessoa física cadastrada com sucesso", result);
    }

    @Test
    void cadastrarPessoaFisica_deveRetornarConflito_quandoReceberUmaPessoaFisicaJaCadastrada() {
        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder().build();
        Mockito.when(pessoaFisicaRepository.getReferenceById(TestConstants.CPF_SO_NUMERO)).thenReturn(pessoaFisica);
        final Exception result = Assertions.assertThrows(ClientServiceException.class, () -> pessoaFisicaService.cadastrarPessoaFisica(pessoaFisica));
        String expected = "Falha no cadastro do objeto pessoa física, CPF já esta em uso";
        Assertions.assertEquals(expected, result.getMessage());
    }

    @Test
    void atualizarPessoaFisica_deveRetornarObjetoAtualizado_quandoReceberUmaPessoaFisicaJaCadastrada() {
        PessoaFisica pessoaJuridica = PessoaFisicaBuilder.builder().build();
        PessoaFisica expected = PessoaFisicaBuilder.builder().build();
        Mockito.when(pessoaFisicaRepository.getReferenceById(expected.getCpf())).thenReturn(pessoaJuridica);
        PessoaFisica result = pessoaFisicaService.atualizarPessoaFisica(expected);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void atualizarPessoaJuridica_deveRetornarNaoAchado_quandoReceberUmaPessoaJuridicaNaoCadastrada() {
        PessoaFisica pessoaFisica = PessoaFisicaBuilder.builder().build();
        Mockito.when(pessoaFisicaRepository.getReferenceById(pessoaFisica.getCpf())).thenThrow(EntityNotFoundException.class);
        final Exception result = Assertions.assertThrows(ClientServiceException.class, () -> pessoaFisicaService.atualizarPessoaFisica(pessoaFisica));
        String expected = "Falha na atualização do objeto pessoa física, objeto não existe";
        Assertions.assertEquals(expected, result.getMessage());
    }

}