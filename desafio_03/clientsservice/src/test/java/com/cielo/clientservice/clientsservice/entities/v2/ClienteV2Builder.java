package com.cielo.clientservice.clientsservice.entities.v1;

import com.cielo.clientservice.clientsservice.entities.clientes.v1.Cliente;
import com.cielo.clientservice.clientsservice.utils.TestConstants;

import java.time.LocalDateTime;

public class ClienteBuilder {
    private Cliente cliente;

    private ClienteBuilder() {
        cliente = new Cliente();
        cliente.setEmail(TestConstants.EMAIL);
        cliente.setNome(TestConstants.NOME);
        cliente.setMerchantCategoryCode(TestConstants.MCC);
        cliente.setAtualizadoEm(TestConstants.NOW);
    }

    public static ClienteBuilder builder() {
        return new ClienteBuilder();
    }

    public ClienteBuilder withEmail(String email) {
        cliente.setEmail(email);
        return this;
    }

    public ClienteBuilder withNome(String nome) {
        cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder withMerchantCategoryCode(String merchantCategoryCode) {
        cliente.setMerchantCategoryCode(merchantCategoryCode);
        return this;
    }

    public ClienteBuilder withAtualizadoEm(LocalDateTime atualizadoEm) {
        cliente.setAtualizadoEm(atualizadoEm);
        return this;
    }

    public Cliente build() {
        return cliente;
    }
}
