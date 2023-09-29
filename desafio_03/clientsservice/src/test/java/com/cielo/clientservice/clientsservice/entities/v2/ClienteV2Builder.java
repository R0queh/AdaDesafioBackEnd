package com.cielo.clientservice.clientsservice.entities.v2;

import com.cielo.clientservice.clientsservice.entities.clientes.v2.ClienteV2;
import com.cielo.clientservice.clientsservice.utils.TestConstants;

public class ClienteV2Builder {
    private ClienteV2 cliente;

    private ClienteV2Builder() {
        cliente = new ClienteV2();
        cliente.setEmail(TestConstants.EMAIL);
        cliente.setNome(TestConstants.NOME);
        cliente.setMerchantCategoryCode(TestConstants.MCC);
    }

    public static ClienteV2Builder builder() {
        return new ClienteV2Builder();
    }

    public ClienteV2Builder withEmail(String email) {
        cliente.setEmail(email);
        return this;
    }

    public ClienteV2Builder withNome(String nome) {
        cliente.setNome(nome);
        return this;
    }

    public ClienteV2Builder withMerchantCategoryCode(String merchantCategoryCode) {
        cliente.setMerchantCategoryCode(merchantCategoryCode);
        return this;
    }

    public ClienteV2 build() {
        return cliente;
    }
}
