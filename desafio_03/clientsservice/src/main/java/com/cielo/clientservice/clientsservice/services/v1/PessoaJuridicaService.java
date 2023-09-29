package com.cielo.clientservice.clientsservice.services.v1;

import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaJuridica;
import com.cielo.clientservice.clientsservice.exceptions.ClientServiceException;
import com.cielo.clientservice.clientsservice.repositories.v1.PessoaJuridicaRepository;
import com.cielo.clientservice.clientsservice.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PessoaJuridicaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaJuridicaService.class);
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    public PessoaJuridicaService(PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    public PessoaJuridica buscarPessoaJuridica(final String cnpj){
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

    public String cadastrarPessoaJuridica(PessoaJuridica pessoaJuridica){
        LOGGER.info("Cadastrando pessoa jurídica de email {}, nome {} e nome fantasia {}",
                pessoaJuridica.getEmail(),
                pessoaJuridica.getNome(),
                pessoaJuridica.getNomeFantasia());
        try{
            pessoaJuridica.setCpf(pessoaJuridica.getCpf().replaceAll("[^a-zA-Z0-9]", ""));
            pessoaJuridicaRepository.getReferenceById(pessoaJuridica.getCnpj());
        }catch (Exception exception){
            pessoaJuridica.setAtualizadoEm(LocalDateTime.now());
            pessoaJuridicaRepository.save(pessoaJuridica);
            return "Pessoa jurídica cadastrada com sucesso";
        }
        LOGGER.info("Erro ao cadastrar pessoa jurídica de email {}, nome {} e nome fantasia {}, CNPJ já está em uso",
                pessoaJuridica.getEmail(),
                pessoaJuridica.getNome(),
                pessoaJuridica.getNomeFantasia());
            throw ClientServiceException.conflito(Constants.PESSOA_JURIDICA.toLowerCase(), Constants.CNPJ);
    }

    public PessoaJuridica atualizarPessoaJuridica(PessoaJuridica pessoaJuridica){
        LOGGER.info("Atualizando pessoa jurídica de email {}, nome {} e nome fantasia {}",
                pessoaJuridica.getEmail(),
                pessoaJuridica.getNome(),
                pessoaJuridica.getNomeFantasia());
        try{
            PessoaJuridica referenceById = pessoaJuridicaRepository.getReferenceById(pessoaJuridica.getCnpj());
            BeanUtils.copyProperties(pessoaJuridica, referenceById);
            referenceById.setAtualizadoEm(LocalDateTime.now());
            pessoaJuridicaRepository.save(referenceById);
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
