package com.cielo.clientservice.clientsservice.controllers.v2;

import com.cielo.clientservice.clientsservice.controllers.swagger.v1.PessoaJuridicaSwaggerController;
import com.cielo.clientservice.clientsservice.controllers.swagger.v2.PessoaJuridicaV2SwaggerController;
import com.cielo.clientservice.clientsservice.entities.clientes.v2.PessoaJuridicaV2;
import com.cielo.clientservice.clientsservice.services.v2.PessoaJuridicaV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/company")
public class PessoaJuridicaV2Controller implements PessoaJuridicaV2SwaggerController {

    private final PessoaJuridicaV2Service pessoaJuridicaService;

    @Autowired
    public PessoaJuridicaV2Controller(PessoaJuridicaV2Service pessoaJuridicaService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
    }


    @Override
    @GetMapping
    public ResponseEntity<PessoaJuridicaV2> buscarPessoaJuridica(@RequestParam String cnpj){
      return ResponseEntity.ok(pessoaJuridicaService.buscarPessoaJuridica(cnpj));
    };

    @Override
    @DeleteMapping
    public ResponseEntity<String> deletarPessoaJuridica(@RequestParam final String cnpj){
        return ResponseEntity.ok(pessoaJuridicaService.deletePessoaJuridica(cnpj));
    }

    @Override
    @PostMapping
    public ResponseEntity<String> cadastrarPessoaJuridica(@RequestBody PessoaJuridicaV2 pessoaJuridica){
        return new ResponseEntity<>(pessoaJuridicaService.cadastrarPessoaJuridica(pessoaJuridica),HttpStatus.CREATED);
    };

    @Override
    @PatchMapping
    public ResponseEntity<PessoaJuridicaV2> atualizarPessoaJuridica(@RequestBody PessoaJuridicaV2 pessoaJuridica){
        return new ResponseEntity<>(pessoaJuridicaService.atualizarPessoaJuridica(pessoaJuridica), HttpStatus.OK);
    }
}
