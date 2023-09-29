package com.cielo.clientservice.clientsservice.controllers;

import com.cielo.clientservice.clientsservice.controllers.swagger.PessoaJuridicaSwaggerController;
import com.cielo.clientservice.clientsservice.entities.clientes.PessoaJuridica;
import com.cielo.clientservice.clientsservice.services.PessoaJuridicaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class PessoaJuridicaController implements PessoaJuridicaSwaggerController {

    private final PessoaJuridicaService pessoaJuridicaService;

    @Autowired
    public PessoaJuridicaController(PessoaJuridicaService pessoaJuridicaService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
    }


    @Override
    @GetMapping
    public ResponseEntity<PessoaJuridica> buscarPessoaJuridica(@RequestParam String cnpj){
      return ResponseEntity.ok(pessoaJuridicaService.buscarPessoaJuridica(cnpj));
    };

    @Override
    @DeleteMapping
    public ResponseEntity<String> deletarPessoaJuridica(@RequestParam final String cnpj){
        return ResponseEntity.ok(pessoaJuridicaService.deletePessoaJuridica(cnpj));
    }

    @Override
    @PostMapping
    public ResponseEntity<String> cadastrarPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica){
        return new ResponseEntity<>(pessoaJuridicaService.cadastrarPessoaJuridica(pessoaJuridica),HttpStatus.CREATED);
    };

    @Override
    @PatchMapping
    public ResponseEntity<PessoaJuridica> atualizarPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica){
        return new ResponseEntity<>(pessoaJuridicaService.atualizarPessoaJuridica(pessoaJuridica), HttpStatus.OK);
    }
}
