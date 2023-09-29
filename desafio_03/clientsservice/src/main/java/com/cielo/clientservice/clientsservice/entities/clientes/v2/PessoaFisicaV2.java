package com.cielo.clientservice.clientsservice.entities.clientes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;


@Entity
public final class PessoaFisica extends Cliente {

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
        PessoaFisica that = (PessoaFisica) object;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
