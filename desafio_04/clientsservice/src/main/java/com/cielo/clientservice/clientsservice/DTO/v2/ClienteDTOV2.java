package com.cielo.clientservice.clientsservice.DTO.v2;

import java.util.Objects;

public class ClienteDTOV2 {

    private String email;
    private String nome;
    private String merchantCategoryCode;
    private String cpf;
    private String cnpj;
    private String nomeFantasia;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClienteDTOV2 that = (ClienteDTOV2) object;
        return Objects.equals(email, that.email) && Objects.equals(nome, that.nome) && Objects.equals(merchantCategoryCode, that.merchantCategoryCode) && Objects.equals(cpf, that.cpf) && Objects.equals(cnpj, that.cnpj) && Objects.equals(nomeFantasia, that.nomeFantasia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, nome, merchantCategoryCode, cpf, cnpj, nomeFantasia);
    }
}
