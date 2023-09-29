package com.cielo.clientservice.clientsservice.entities.v2;

import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaJuridica;
import com.cielo.clientservice.clientsservice.entities.clientes.v2.PessoaJuridicaV2;
import com.cielo.clientservice.clientsservice.utils.TestConstants;

import java.time.LocalDateTime;

public class PessoaJuridicaV2Builder {
    private PessoaJuridicaV2 pessoaJuridica;

    private PessoaJuridicaV2Builder() {
        pessoaJuridica = new PessoaJuridicaV2();
        pessoaJuridica.setEmail(TestConstants.EMAIL);
        pessoaJuridica.setNome(TestConstants.NOME);
        pessoaJuridica.setMerchantCategoryCode(TestConstants.MCC);
        pessoaJuridica.setCpf(TestConstants.CPF);
        pessoaJuridica.setCnpj(TestConstants.CNPJ);
        pessoaJuridica.setNomeFantasia(TestConstants.NOME_FANTASIA);
    }

    public static PessoaJuridicaV2Builder builder() {
        return new PessoaJuridicaV2Builder();
    }

    public PessoaJuridicaV2Builder withEmail(String email) {
        pessoaJuridica.setEmail(email);
        return this;
    }

    public PessoaJuridicaV2Builder withNome(String nome) {
        pessoaJuridica.setNome(nome);
        return this;
    }

    public PessoaJuridicaV2Builder withMerchantCategoryCode(String merchantCategoryCode) {
        pessoaJuridica.setMerchantCategoryCode(merchantCategoryCode);
        return this;
    }

    public PessoaJuridicaV2Builder withCnpj(String cnpj) {
        pessoaJuridica.setCnpj(cnpj);
        return this;
    }

    public PessoaJuridicaV2Builder withNomeFantasia(String nomeFantasia) {
        pessoaJuridica.setNomeFantasia(nomeFantasia);
        return this;
    }

    public PessoaJuridicaV2Builder withCpf(String cpf) {
        pessoaJuridica.setCpf(cpf);
        return this;
    }

    public PessoaJuridicaV2 build() {
        return pessoaJuridica;
    }
}
