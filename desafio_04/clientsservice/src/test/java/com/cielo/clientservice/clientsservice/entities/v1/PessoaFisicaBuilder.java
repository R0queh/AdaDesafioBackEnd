package com.cielo.clientservice.clientsservice.entities.v1;

import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaFisica;
import com.cielo.clientservice.clientsservice.utils.TestConstants;

import java.time.LocalDateTime;

public class PessoaFisicaBuilder {
    private PessoaFisica pessoaFisica;

    private PessoaFisicaBuilder() {
        pessoaFisica = new PessoaFisica();
        pessoaFisica.setEmail(TestConstants.EMAIL);
        pessoaFisica.setNome(TestConstants.NOME);
        pessoaFisica.setMerchantCategoryCode(TestConstants.MCC);
        pessoaFisica.setAtualizadoEm(TestConstants.NOW);
        pessoaFisica.setCpf(TestConstants.CPF);
    }

    public static PessoaFisicaBuilder builder() {
        return new PessoaFisicaBuilder();
    }

    public PessoaFisicaBuilder withEmail(String email) {
        pessoaFisica.setEmail(email);
        return this;
    }

    public PessoaFisicaBuilder withNome(String nome) {
        pessoaFisica.setNome(nome);
        return this;
    }

    public PessoaFisicaBuilder withMerchantCategoryCode(String merchantCategoryCode) {
        pessoaFisica.setMerchantCategoryCode(merchantCategoryCode);
        return this;
    }

    public PessoaFisicaBuilder withAtualizadoEm(LocalDateTime atualizadoEm) {
        pessoaFisica.setAtualizadoEm(atualizadoEm);
        return this;
    }

    public PessoaFisicaBuilder withCpf(String cpf) {
        pessoaFisica.setCpf(cpf);
        return this;
    }

    public PessoaFisica build() {
        return pessoaFisica;
    }
}
