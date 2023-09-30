package com.cielo.clientservice.clientsservice.entities.v2;

import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaFisica;
import com.cielo.clientservice.clientsservice.entities.clientes.v2.PessoaFisicaV2;
import com.cielo.clientservice.clientsservice.utils.TestConstants;

import java.time.LocalDateTime;

public class PessoaFisicaV2Builder {
    private PessoaFisicaV2 pessoaFisica;

    private PessoaFisicaV2Builder() {
        pessoaFisica = new PessoaFisicaV2();
        pessoaFisica.setEmail(TestConstants.EMAIL);
        pessoaFisica.setNome(TestConstants.NOME);
        pessoaFisica.setMerchantCategoryCode(TestConstants.MCC);
        pessoaFisica.setCpf(TestConstants.CPF);
    }

    public static PessoaFisicaV2Builder builder() {
        return new PessoaFisicaV2Builder();
    }

    public PessoaFisicaV2Builder withEmail(String email) {
        pessoaFisica.setEmail(email);
        return this;
    }

    public PessoaFisicaV2Builder withNome(String nome) {
        pessoaFisica.setNome(nome);
        return this;
    }

    public PessoaFisicaV2Builder withMerchantCategoryCode(String merchantCategoryCode) {
        pessoaFisica.setMerchantCategoryCode(merchantCategoryCode);
        return this;
    }

    public PessoaFisicaV2Builder withCpf(String cpf) {
        pessoaFisica.setCpf(cpf);
        return this;
    }

    public PessoaFisicaV2 build() {
        return pessoaFisica;
    }
}
