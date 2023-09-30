package com.cielo.clientservice.clientsservice.entities.clientes.v2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;


@Entity
public final class PessoaFisicaV2 extends ClienteV2 {

    @CPF
    @Id
    private String cpf;

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
        PessoaFisicaV2 that = (PessoaFisicaV2) object;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
