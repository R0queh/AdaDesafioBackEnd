package com.cielo.clientservice.clientsservice.services.v2;

import com.cielo.clientservice.clientsservice.DTO.ClienteDTO;
import com.cielo.clientservice.clientsservice.DTO.v2.ClienteDTOV2;
import com.cielo.clientservice.clientsservice.exceptions.ClientServiceException;
import com.cielo.clientservice.clientsservice.mappers.ClienteDTOMapper;
import com.cielo.clientservice.clientsservice.repositories.v1.PessoaFisicaRepository;
import com.cielo.clientservice.clientsservice.repositories.v1.PessoaJuridicaRepository;
import com.cielo.clientservice.clientsservice.utils.Constants;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilaDeAtendimentoV2Service {

    final PessoaJuridicaRepository pessoaJuridicaRepository;
    final PessoaFisicaRepository pessoaFisicaRepository;
    final ClienteDTOMapper clienteDTOMapper;
    final SqsTemplate sqsTemplate;

    @Autowired
    public FilaDeAtendimentoV2Service(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaFisicaRepository pessoaFisicaRepository, ClienteDTOMapper clienteDTOMapper, SqsTemplate sqsTemplate) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.clienteDTOMapper = clienteDTOMapper;
        this.sqsTemplate = sqsTemplate;
    }

    public ClienteDTOV2 buscarProximoDaFilaDeAtendimento(){
        Optional<Message<ClienteDTOV2>> clienteDTOMessage = sqsTemplate.receive(Constants.FILA_DE_CLIENTES, ClienteDTOV2.class);
        if (clienteDTOMessage.isPresent()){
            if (ObjectUtils.isEmpty(clienteDTOMessage.get().getPayload().getCnpj())) {
                pessoaFisicaRepository.deleteById(clienteDTOMessage.get().getPayload().getCpf());
            } else {
                pessoaJuridicaRepository.deleteById(clienteDTOMessage.get().getPayload().getCnpj());
            }
            return clienteDTOMessage.get().getPayload();
        };
        throw ClientServiceException.filaVazia();
    }

}
