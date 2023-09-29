package com.cielo.clientservice.clientsservice.entities;

import com.cielo.clientservice.clientsservice.entities.clientes.PessoaJuridica;
import com.cielo.clientservice.clientsservice.utils.TestConstants;

import java.time.LocalDateTime;

public class PessoaJuridicaBuilder {
    private PessoaJuridica pessoaJuridica;

    private PessoaJuridicaBuilder() {
        pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setEmail(TestConstants.EMAIL);
        pessoaJuridica.setNome(TestConstants.NOME);
        pessoaJuridica.setMerchantCategoryCode(TestConstants.MCC);
        pessoaJuridica.setAtualizadoEm(TestConstants.NOW);
        pessoaJuridica.setCpf(TestConstants.CPF);
        pessoaJuridica.setCnpj(TestConstants.CNPJ);
        pessoaJuridica.setNomeFantasia(TestConstants.NOME_FANTASIA);
    }

    public static PessoaJuridicaBuilder builder() {
        return new PessoaJuridicaBuilder();
    }

    public PessoaJuridicaBuilder withEmail(String email) {
        pessoaJuridica.setEmail(email);
        return this;
    }

    public PessoaJuridicaBuilder withNome(String nome) {
        pessoaJuridica.setNome(nome);
        return this;
    }

    public PessoaJuridicaBuilder withMerchantCategoryCode(String merchantCategoryCode) {
        pessoaJuridica.setMerchantCategoryCode(merchantCategoryCode);
        return this;
    }

    public PessoaJuridicaBuilder withAtualizadoEm(LocalDateTime atualizadoEm) {
        pessoaJuridica.setAtualizadoEm(atualizadoEm);
        return this;
    }

    public PessoaJuridicaBuilder withCnpj(String cnpj) {
        pessoaJuridica.setCnpj(cnpj);
        return this;
    }

    public PessoaJuridicaBuilder withNomeFantasia(String nomeFantasia) {
        pessoaJuridica.setNomeFantasia(nomeFantasia);
        return this;
    }

    public PessoaJuridicaBuilder withCpf(String cpf) {
        pessoaJuridica.setCpf(cpf);
        return this;
    }

    public PessoaJuridica build() {
        return pessoaJuridica;
    }
}
