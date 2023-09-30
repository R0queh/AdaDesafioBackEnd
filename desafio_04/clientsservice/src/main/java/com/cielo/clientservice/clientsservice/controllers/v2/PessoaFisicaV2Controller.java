package com.cielo.clientservice.clientsservice.controllers.v2;

import com.cielo.clientservice.clientsservice.controllers.swagger.v1.PessoaFisicaSwaggerController;
import com.cielo.clientservice.clientsservice.controllers.swagger.v2.PessoaFisicaV2SwaggerController;
import com.cielo.clientservice.clientsservice.entities.clientes.v1.PessoaFisica;
import com.cielo.clientservice.clientsservice.entities.clientes.v2.PessoaFisicaV2;
import com.cielo.clientservice.clientsservice.services.v2.PessoaFisicaV2Service;
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
@RequestMapping("/v2/person")
public class PessoaFisicaV2Controller implements PessoaFisicaV2SwaggerController {

    private final PessoaFisicaV2Service pessoaFisicaService;

    @Autowired
    public PessoaFisicaV2Controller(PessoaFisicaV2Service pessoaFisicaService) {
        this.pessoaFisicaService = pessoaFisicaService;
    }

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<PessoaFisicaV2> buscarPessoaFisica(@PathVariable String cpf){
      return ResponseEntity.ok(pessoaFisicaService.buscarPessoaFisica(cpf));
    };

    @Override
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletarPessoaFisica(@PathVariable final String cpf){
        return ResponseEntity.ok(pessoaFisicaService.deletePessoaFisica(cpf));
    }

    @Override
    @PostMapping
    public ResponseEntity<String> cadastroPessoaFisica(@RequestBody @Valid PessoaFisicaV2 pessoaFisica){
        return new ResponseEntity<>(pessoaFisicaService.cadastrarPessoaFisica(pessoaFisica), HttpStatus.CREATED);
    };

    @Override
    @PatchMapping
    public ResponseEntity<PessoaFisicaV2> atualizarPessoaFisica(@RequestBody @Valid PessoaFisicaV2 pessoaFisica){
        return new ResponseEntity<>(pessoaFisicaService.atualizarPessoaFisica(pessoaFisica), HttpStatus.OK);
    }
}
