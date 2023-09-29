package com.cielo.clientservice.clientsservice.repositories;


import com.cielo.clientservice.clientsservice.entities.clientes.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, String> {
}
