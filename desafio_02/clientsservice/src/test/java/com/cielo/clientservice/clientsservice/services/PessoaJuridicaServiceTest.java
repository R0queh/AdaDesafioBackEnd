package com.cielo.clientservice.clientsservice.services;

import com.cielo.clientservice.clientsservice.entities.clientes.PessoaJuridica;
import com.cielo.clientservice.clientsservice.exceptions.ClientServiceException;
import com.cielo.clientservice.clientsservice.repositories.PessoaJuridicaRepository;
import com.cielo.clientservice.clientsservice.entities.PessoaJuridicaBuilder;
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
class PessoaJuridicaServiceTest {

    @Mock
    PessoaJuridicaRepository pessoaJuridicaRepository;
    @InjectMocks PessoaJuridicaService pessoaJuridicaService;

    @Test
    void buscarPessoaJuridica_deveRetornarPessoaJuridica_quandoReceberUmCNPJValido() {
        PessoaJuridica expected = PessoaJuridicaBuilder.builder().build();
        Mockito.when(pessoaJuridicaRepository.getReferenceById(TestConstants.CNPJ)).thenReturn(PessoaJuridicaBuilder.builder().build());
        PessoaJuridica result = pessoaJuridicaService.buscarPessoaJuridica(TestConstants.CNPJ);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void buscarPessoaJuridica_deveRetornarNaoAchado_quandoReceberUmCNPJNaoCadastrado() {
        Mockito.when(pessoaJuridicaRepository.getReferenceById(TestConstants.CNPJ)).thenThrow(EntityNotFoundException.class);
        final Exception result = Assertions.assertThrows(ClientServiceException.class, () -> pessoaJuridicaService.buscarPessoaJuridica(TestConstants.CNPJ));
        String expected = "Pessoa jurídica não encontrada(o)";
        Assertions.assertEquals(expected, result.getMessage());
    }

    @Test
    void deletePessoaJuridica_deveChamarRepositoryUmaVez_quandoReceberUmCNPJValido() {
        pessoaJuridicaService.deletePessoaJuridica(TestConstants.CNPJ);
        Mockito.verify(pessoaJuridicaRepository, Mockito.times(1)).deleteById(TestConstants.CNPJ);
    }

    @Test
    void cadastrarPessoaJuridica_deveRetornarMensagemDeSucesso_quandoReceberUmaPessoaJuridicaValida() {
        PessoaJuridica pessoaJuridica = PessoaJuridicaBuilder.builder().build();
        Mockito.when(pessoaJuridicaRepository.getReferenceById(pessoaJuridica.getCnpj())).thenThrow(EntityNotFoundException.class);
        String result = pessoaJuridicaService.cadastrarPessoaJuridica(pessoaJuridica);
        Assertions.assertEquals("Pessoa jurídica cadastrada com sucesso", result);
    }

    @Test
    void cadastrarPessoaJuridica_deveRetornarConflito_quandoReceberUmaPessoaJuridicaJaCadastrada() {
        PessoaJuridica pessoaJuridica = PessoaJuridicaBuilder.builder().build();
        Mockito.when(pessoaJuridicaRepository.getReferenceById(pessoaJuridica.getCnpj())).thenReturn(pessoaJuridica);
        final Exception result = Assertions.assertThrows(ClientServiceException.class, () -> pessoaJuridicaService.cadastrarPessoaJuridica(pessoaJuridica));
        String expected = "Falha no cadastro do objeto pessoa jurídica, CNPJ já esta em uso";
        Assertions.assertEquals(expected, result.getMessage());
    }

    @Test
    void atualizarPessoaJuridica_deveRetornarObjetoAtualizado_quandoReceberUmaPessoaJuridicaJaCadastrada() {
        PessoaJuridica pessoaJuridica = PessoaJuridicaBuilder.builder().build();
        PessoaJuridica expected = PessoaJuridicaBuilder.builder().build();
        Mockito.when(pessoaJuridicaRepository.getReferenceById(expected.getCnpj())).thenReturn(pessoaJuridica);
        PessoaJuridica result = pessoaJuridicaService.atualizarPessoaJuridica(expected);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void atualizarPessoaJuridica_deveRetornarNaoAchado_quandoReceberUmaPessoaJuridicaNaoCadastrada() {
        PessoaJuridica pessoaJuridica = PessoaJuridicaBuilder.builder().build();
        Mockito.when(pessoaJuridicaRepository.getReferenceById(pessoaJuridica.getCnpj())).thenThrow(EntityNotFoundException.class);
        final Exception result = Assertions.assertThrows(ClientServiceException.class, () -> pessoaJuridicaService.atualizarPessoaJuridica(pessoaJuridica));
        String expected = "Falha na atualização do objeto pessoa jurídica, objeto não existe";
        Assertions.assertEquals(expected, result.getMessage());
    }
}