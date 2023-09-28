package com.cielo.clientservice.clientsservice.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@MappedSuperclass
public sealed class Cliente permits PessoaJuridica, PessoaFisica {

    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,3}$")
    private String email;
    @Size(max = 50, message = "Atributo nome não deve passar de 50 dígitos")
    private String nome;
    @Size(max = 4, min = 4, message = "Atributo merchantCategoryCode tem que ter 4 dígitos")
    private String merchantCategoryCode;
    private LocalDateTime atualizadoEm;

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

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
