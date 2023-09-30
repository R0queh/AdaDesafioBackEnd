package com.cielo.clientservice.clientsservice.repositories.v1;


import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, String> {
}
