package com.cielo.clientservice.clientsservice.services.v1;

import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaFisica;
import com.cielo.clientservice.clientsservice.exceptions.ClientServiceException;
import com.cielo.clientservice.clientsservice.repositories.v1.PessoaFisicaRepository;
import com.cielo.clientservice.clientsservice.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PessoaFisicaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaFisicaService.class);
    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    public PessoaFisica buscarPessoaFisica(final String cpf){
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

    public String cadastrarPessoaFisica(PessoaFisica pessoaFisica){
        LOGGER.info("Cadastrando pessoa física de email {} e nome {}", pessoaFisica.getEmail(), pessoaFisica.getNome());
        try{
            pessoaFisica.setCpf(pessoaFisica.getCpf().replaceAll("[^a-zA-Z0-9]", ""));
            pessoaFisicaRepository.getReferenceById(pessoaFisica.getCpf());
        }catch (Exception exception){
            pessoaFisica.setAtualizadoEm(LocalDateTime.now());
            pessoaFisicaRepository.save(pessoaFisica);
            return "Pessoa física cadastrada com sucesso";
        }
        LOGGER.error("Erro ao cadastrar pessoa fisica de email {} e nome {}, CPF já está em uso",
                pessoaFisica.getEmail(),
                pessoaFisica.getNome());
        throw ClientServiceException.conflito(Constants.PESSOA_FISICA.toLowerCase(), Constants.CPF);
    }

    public PessoaFisica atualizarPessoaFisica(PessoaFisica pessoaFisica){
        LOGGER.info("Atualizando pessoa fisica de email {} e nome {}",
                pessoaFisica.getEmail(),
                pessoaFisica.getNome());
        try{
            PessoaFisica referenceById = pessoaFisicaRepository.getReferenceById(pessoaFisica.getCpf());
            BeanUtils.copyProperties(pessoaFisica, referenceById);
            referenceById.setAtualizadoEm(LocalDateTime.now());
            pessoaFisicaRepository.save(referenceById);
            return referenceById;
        }catch (Exception exception){
            LOGGER.error("Erro ao atualizar pessoa física de email {} e nome {}",
                    pessoaFisica.getEmail(),
                    pessoaFisica.getNome());
            throw ClientServiceException.naoAchadoAtualizacao(Constants.PESSOA_FISICA.toLowerCase());
        }
    }
}
