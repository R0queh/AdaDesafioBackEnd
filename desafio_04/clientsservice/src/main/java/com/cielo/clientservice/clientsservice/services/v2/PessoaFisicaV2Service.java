package com.cielo.clientservice.clientsservice.services.v2;

import com.cielo.clientservice.clientsservice.entities.clientes.v2.PessoaFisicaV2;
import com.cielo.clientservice.clientsservice.exceptions.ClientServiceException;
import com.cielo.clientservice.clientsservice.mappers.ClienteDTOV2Mapper;
import com.cielo.clientservice.clientsservice.repositories.v2.PessoaFisicaV2Repository;
import com.cielo.clientservice.clientsservice.utils.Constants;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaV2Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaFisicaV2Service.class);
    private final PessoaFisicaV2Repository pessoaFisicaRepository;
    private final SqsTemplate sqsTemplate;
    private final ClienteDTOV2Mapper clienteDTOV2Mapper;

    @Autowired
    public PessoaFisicaV2Service(PessoaFisicaV2Repository pessoaFisicaRepository, SqsTemplate sqsTemplate, ClienteDTOV2Mapper clienteDTOV2Mapper) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.sqsTemplate = sqsTemplate;
        this.clienteDTOV2Mapper = clienteDTOV2Mapper;
    }

    public PessoaFisicaV2 buscarPessoaFisica(final String cpf){
        LOGGER.info("Buscando pessoa física");
        try{
            return pessoaFisicaRepository.getReferenceById(cpf);
        }catch (Exception exception){
            LOGGER.error("Pessoa jurídica não encontrada", exception);
            throw ClientServiceException.naoAchado(Constants.PESSOA_FISICA);
        }
    }

    public String deletePessoaFisica(final String cpf){
        LOGGER.info("Deletando pessoa física");
        pessoaFisicaRepository.deleteById(cpf);
        return "Pessoa física deletada com sucesso";
    }

    public String cadastrarPessoaFisica(PessoaFisicaV2 pessoaFisica){
        LOGGER.info("Cadastrando pessoa física de email {} e nome {}", pessoaFisica.getEmail(), pessoaFisica.getNome());
        try{
            pessoaFisica.setCpf(pessoaFisica.getCpf().replaceAll("[^a-zA-Z0-9]", ""));
            pessoaFisicaRepository.getReferenceById(pessoaFisica.getCpf());
        }catch (Exception exception){
            pessoaFisicaRepository.save(pessoaFisica);
            LOGGER.info("Mandando cliente cadastrado para a fila");
            sqsTemplate.send(to -> to.queue(Constants.FILA_DE_CLIENTES).payload(clienteDTOV2Mapper.toClienteDTO(pessoaFisica)));
            return "Pessoa física cadastrada com sucesso";
        }
        LOGGER.error("Erro ao cadastrar pessoa fisica de email {} e nome {}, CPF já está em uso",
                pessoaFisica.getEmail(),
                pessoaFisica.getNome());
        throw ClientServiceException.conflito(Constants.PESSOA_FISICA.toLowerCase(), Constants.CPF);
    }

    public PessoaFisicaV2 atualizarPessoaFisica(PessoaFisicaV2 pessoaFisica){
        LOGGER.info("Atualizando pessoa fisica de email {} e nome {}",
                pessoaFisica.getEmail(),
                pessoaFisica.getNome());
        try{
            PessoaFisicaV2 referenceById = pessoaFisicaRepository.getReferenceById(pessoaFisica.getCpf());
            BeanUtils.copyProperties(pessoaFisica, referenceById);
            pessoaFisicaRepository.save(referenceById);
            LOGGER.info("Mandando cliente atualizado para a fila");
            sqsTemplate.send(to -> to.queue(Constants.FILA_DE_CLIENTES).payload(clienteDTOV2Mapper.toClienteDTO(referenceById)));
            return referenceById;
        }catch (Exception exception){
            LOGGER.error("Erro ao atualizar pessoa física de email {} e nome {}",
                    pessoaFisica.getEmail(),
                    pessoaFisica.getNome());
            throw ClientServiceException.naoAchadoAtualizacao(Constants.PESSOA_FISICA.toLowerCase());
        }
    }
}
