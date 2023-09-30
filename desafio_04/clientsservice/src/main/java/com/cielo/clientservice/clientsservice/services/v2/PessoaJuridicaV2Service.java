package com.cielo.clientservice.clientsservice.services.v2;

import com.cielo.clientservice.clientsservice.entities.clientes.v2.PessoaJuridicaV2;
import com.cielo.clientservice.clientsservice.exceptions.ClientServiceException;
import com.cielo.clientservice.clientsservice.mappers.ClienteDTOV2Mapper;
import com.cielo.clientservice.clientsservice.repositories.v2.PessoaJuridicaV2Repository;
import com.cielo.clientservice.clientsservice.utils.Constants;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaJuridicaV2Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaJuridicaV2Service.class);
    private final PessoaJuridicaV2Repository pessoaJuridicaRepository;
    private final SqsTemplate sqsTemplate;
    private final ClienteDTOV2Mapper clienteDTOV2Mapper;

    @Autowired
    public PessoaJuridicaV2Service(PessoaJuridicaV2Repository pessoaJuridicaRepository, SqsTemplate sqsTemplate, ClienteDTOV2Mapper clienteDTOV2Mapper) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.sqsTemplate = sqsTemplate;
        this.clienteDTOV2Mapper = clienteDTOV2Mapper;
    }

    public PessoaJuridicaV2 buscarPessoaJuridica(final String cnpj){
        LOGGER.info("Buscando pessoa jurídica");
        try{
            return pessoaJuridicaRepository.getReferenceById(cnpj);
        }catch (Exception exception){
            LOGGER.error("Pessoa jurídica não encontrada", exception);
            throw ClientServiceException.naoAchado(Constants.PESSOA_JURIDICA);
        }
    }

    public String deletePessoaJuridica(final String cnpj){
        LOGGER.info("Deletando pessoa jurídica");
        pessoaJuridicaRepository.deleteById(cnpj);
        return "Pessoa jurídica deletada com sucesso";
    }

    public String cadastrarPessoaJuridica(PessoaJuridicaV2 pessoaJuridica){
        LOGGER.info("Cadastrando pessoa jurídica de email {}, nome {} e nome fantasia {}",
                pessoaJuridica.getEmail(),
                pessoaJuridica.getNome(),
                pessoaJuridica.getNomeFantasia());
        try{
            pessoaJuridica.setCpf(pessoaJuridica.getCpf().replaceAll("[^a-zA-Z0-9]", ""));
            pessoaJuridicaRepository.getReferenceById(pessoaJuridica.getCnpj());
        }catch (Exception exception){
            pessoaJuridicaRepository.save(pessoaJuridica);
            LOGGER.info("Mandando cliente cadastrado para a fila");
            sqsTemplate.send(to -> to.queue(Constants.FILA_DE_CLIENTES).payload(clienteDTOV2Mapper.toClienteDTO(pessoaJuridica)));
            return "Pessoa jurídica cadastrada com sucesso";
        }
        LOGGER.info("Erro ao cadastrar pessoa jurídica de email {}, nome {} e nome fantasia {}, CNPJ já está em uso",
                pessoaJuridica.getEmail(),
                pessoaJuridica.getNome(),
                pessoaJuridica.getNomeFantasia());
            throw ClientServiceException.conflito(Constants.PESSOA_JURIDICA.toLowerCase(), Constants.CNPJ);
    }

    public PessoaJuridicaV2 atualizarPessoaJuridica(PessoaJuridicaV2 pessoaJuridica){
        LOGGER.info("Atualizando pessoa jurídica de email {}, nome {} e nome fantasia {}",
                pessoaJuridica.getEmail(),
                pessoaJuridica.getNome(),
                pessoaJuridica.getNomeFantasia());
        try{
            PessoaJuridicaV2 referenceById = pessoaJuridicaRepository.getReferenceById(pessoaJuridica.getCnpj());
            BeanUtils.copyProperties(pessoaJuridica, referenceById);
            pessoaJuridicaRepository.save(referenceById);
            LOGGER.info("Mandando cliente atualizado para a fila");
            sqsTemplate.send(to -> to.queue(Constants.FILA_DE_CLIENTES).payload(clienteDTOV2Mapper.toClienteDTO(pessoaJuridica)));
            return referenceById;
        }catch (Exception exception){
            LOGGER.info("Erro ao atualizar pessoa jurídica de email {}, nome {} e nome fantasia {}, Pessoa jurídica não existe",
                    pessoaJuridica.getEmail(),
                    pessoaJuridica.getNome(),
                    pessoaJuridica.getNomeFantasia());
            throw ClientServiceException.naoAchadoAtualizacao(Constants.PESSOA_JURIDICA.toLowerCase());
        }
    }
}
