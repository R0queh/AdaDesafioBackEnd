package com.cielo.clientservice.clientsservice.mappers;

import com.cielo.clientservice.clientsservice.DTO.ClienteDTO;
import com.cielo.clientservice.clientsservice.DTO.v2.ClienteDTOV2;
import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaFisica;
import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaJuridica;
import com.cielo.clientservice.clientsservice.entities.clientes.v2.PessoaFisicaV2;
import com.cielo.clientservice.clientsservice.entities.clientes.v2.PessoaJuridicaV2;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteDTOV2Mapper {

    ClienteDTOV2 toClienteDTO(PessoaJuridicaV2 pessoaJuridica);
    ClienteDTOV2 toClienteDTO(PessoaFisicaV2 pessoaFisica);
}
