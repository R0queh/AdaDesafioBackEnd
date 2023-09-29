package com.cielo.clientservice.clientsservice.controllers;

import com.cielo.clientservice.clientsservice.controllers.swagger.PessoaFisicaSwaggerController;
import com.cielo.clientservice.clientsservice.entities.clientes.PessoaFisica;
import com.cielo.clientservice.clientsservice.services.PessoaFisicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PessoaFisicaController implements PessoaFisicaSwaggerController {

    private final PessoaFisicaService pessoaFisicaService;

    @Autowired
    public PessoaFisicaController(PessoaFisicaService pessoaFisicaService) {
        this.pessoaFisicaService = pessoaFisicaService;
    }

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<PessoaFisica> buscarPessoaFisica(@PathVariable String cpf){
      return ResponseEntity.ok(pessoaFisicaService.buscarPessoaFisica(cpf));
    };

    @Override
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletarPessoaFisica(@PathVariable final String cpf){
        return ResponseEntity.ok(pessoaFisicaService.deletePessoaFisica(cpf));
    }

    @Override
    @PostMapping
    public ResponseEntity<String> cadastroPessoaFisica(@RequestBody @Valid PessoaFisica pessoaFisica){
        return new ResponseEntity<>(pessoaFisicaService.cadastrarPessoaFisica(pessoaFisica), HttpStatus.CREATED);
    };

    @Override
    @PatchMapping
    public ResponseEntity<PessoaFisica> atualizarPessoaFisica(@RequestBody @Valid PessoaFisica pessoaFisica){
        return new ResponseEntity<>(pessoaFisicaService.atualizarPessoaFisica(pessoaFisica), HttpStatus.OK);
    }
}
