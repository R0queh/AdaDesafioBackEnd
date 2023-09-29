package com.cielo.clientservice.clientsservice.entities.clientes.v2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Entity
@Validated
public final class PessoaJuridicaV2 extends ClienteV2 {

    @Id
    @CNPJ(message = "CNPJ inválido, passe somente números ou use o padrão XX.XXX.XXX/0001-XX")
    private String cnpj;
    @Size(max = 50, message = "Atributo nome não deve passar de 50 dígitos")
    private String nomeFantasia;
    @CPF(message = "CPF inválido, passe somente números ou use o padrão XXX.XXX.XXX-XX")
    private String cpf;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PessoaJuridicaV2 that = (PessoaJuridicaV2) object;
        return Objects.equals(cnpj, that.cnpj) && Objects.equals(nomeFantasia, that.nomeFantasia) && Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj, nomeFantasia, cpf);
    }
}
