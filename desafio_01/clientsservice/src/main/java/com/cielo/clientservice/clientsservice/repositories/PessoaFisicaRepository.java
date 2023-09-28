package com.cielo.clientservice.clientsservice.repositories;


import com.cielo.clientservice.clientsservice.entities.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, String> {
}
