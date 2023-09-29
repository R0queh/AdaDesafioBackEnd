package com.cielo.clientservice.clientsservice.mappers;

import com.cielo.clientservice.clientsservice.DTO.ClienteDTO;
import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaFisica;
import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaJuridica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteDTOMapper {

    ClienteDTO toClienteDTO(PessoaJuridica pessoaJuridica);
    ClienteDTO toClienteDTO(PessoaFisica pessoaFisica);
}
