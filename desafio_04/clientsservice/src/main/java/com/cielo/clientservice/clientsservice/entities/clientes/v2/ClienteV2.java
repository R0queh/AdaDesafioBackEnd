package com.cielo.clientservice.clientsservice.entities.clientes.v2;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public sealed class ClienteV2 permits PessoaJuridicaV2, PessoaFisicaV2 {

    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,3}$")
    private String email;
    @Size(max = 50, message = "Atributo nome não deve passar de 50 dígitos")
    private String nome;
    @Size(max = 4, min = 4, message = "Atributo merchantCategoryCode tem que ter 4 dígitos")
    private String merchantCategoryCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }
}
