package com.cielo.clientservice.clientsservice.repositories;


import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaJuridica;
import com.cielo.clientservice.clientsservice.entities.clientes.v2.PessoaJuridicaV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaV2Repository extends JpaRepository<PessoaJuridicaV2, String> {
}
