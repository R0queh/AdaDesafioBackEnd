package com.cielo.clientservice.clientsservice.services.v1;

import com.cielo.clientservice.clientsservice.DTO.ClienteDTO;
import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaFisica;
import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaJuridica;
import com.cielo.clientservice.clientsservice.entities.fila.Fila;
import com.cielo.clientservice.clientsservice.mappers.ClienteDTOMapper;
import com.cielo.clientservice.clientsservice.repositories.v1.PessoaFisicaRepository;
import com.cielo.clientservice.clientsservice.repositories.v1.PessoaJuridicaRepository;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FilaDeAtendimentoService {

    final PessoaJuridicaRepository pessoaJuridicaRepository;
    final PessoaFisicaRepository pessoaFisicaRepository;
    final ClienteDTOMapper clienteDTOMapper;
    final SqsTemplate sqsTemplate;

    @Autowired
    public FilaDeAtendimentoService(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaFisicaRepository pessoaFisicaRepository, ClienteDTOMapper clienteDTOMapper, SqsTemplate sqsTemplate) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.clienteDTOMapper = clienteDTOMapper;
        this.sqsTemplate = sqsTemplate;
    }

    public ClienteDTO buscarProximoDaFilaDeAtendimento(){
        List<Object> clientes = buscarTodosOsClientes();
        Fila<ClienteDTO> filaDeClientes = montarFilaDeClientes(clientes);
        ClienteDTO clienteDTO = filaDeClientes.pegarPrimeiroDaFila();
        removerClienteAtendidoDoCadastro(clienteDTO);
        return clienteDTO;
    }


    private List<Object> buscarTodosOsClientes(){
        List<PessoaFisica> listaPessoaFisica = pessoaFisicaRepository.findAll();
        List<PessoaJuridica> listaPessoaJuridica = pessoaJuridicaRepository.findAll();
        List<Object> listaCombinada = new ArrayList<>();
        listaCombinada.addAll(listaPessoaJuridica);
        listaCombinada.addAll(listaPessoaFisica);
        return listaCombinada;
    }

    private Fila<ClienteDTO> montarFilaDeClientes(List<Object> clientes) {
        Fila<ClienteDTO> fila = new Fila<>(new ClienteDTO[clientes.size() + 5]);
        Collections.sort(clientes, (a, b) -> {
            LocalDateTime dateA = (a instanceof PessoaFisica) ? ((PessoaFisica) a).getAtualizadoEm() : ((PessoaJuridica) a).getAtualizadoEm();
            LocalDateTime dateB = (b instanceof PessoaFisica) ? ((PessoaFisica) b).getAtualizadoEm() : ((PessoaJuridica) b).getAtualizadoEm();
            return dateA.compareTo(dateB);
        });
        clientes.forEach(cliente -> fila.AdicionarAFila((cliente instanceof PessoaFisica) ? clienteDTOMapper.toClienteDTO((PessoaFisica) cliente) : clienteDTOMapper.toClienteDTO((PessoaJuridica) cliente)));
        return fila;
    }

    private void removerClienteAtendidoDoCadastro(ClienteDTO clienteDTO) {
        if (ObjectUtils.isEmpty(clienteDTO.getCnpj())) {
            pessoaFisicaRepository.deleteById(clienteDTO.getCpf());
        } else {
            pessoaJuridicaRepository.deleteById(clienteDTO.getCnpj());
        }
    }

}
