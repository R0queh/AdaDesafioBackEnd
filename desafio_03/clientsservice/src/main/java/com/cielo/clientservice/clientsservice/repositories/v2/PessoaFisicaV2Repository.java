package com.cielo.clientservice.clientsservice.repositories.v2;


import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaFisica;
import com.cielo.clientservice.clientsservice.entities.clientes.v2.PessoaFisicaV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaV2Repository extends JpaRepository<PessoaFisicaV2, String> {
}
