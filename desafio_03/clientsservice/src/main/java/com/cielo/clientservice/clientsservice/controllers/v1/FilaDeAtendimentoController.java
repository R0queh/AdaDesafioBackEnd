package com.cielo.clientservice.clientsservice.controllers;

import com.cielo.clientservice.clientsservice.DTO.ClienteDTO;
import com.cielo.clientservice.clientsservice.controllers.swagger.FilaDeAtendimentoSwaggerController;
import com.cielo.clientservice.clientsservice.services.FilaDeAtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("filaDeAtendimento")
public class FilaDeAtendimentoController implements FilaDeAtendimentoSwaggerController {

    final FilaDeAtendimentoService filaDeAtendimentoService;

    @Autowired
    public FilaDeAtendimentoController(FilaDeAtendimentoService filaDeAtendimentoService) {
        this.filaDeAtendimentoService = filaDeAtendimentoService;
    }

    @Override
    @GetMapping
    public ClienteDTO buscarFilaDeAtendimento(){
        return filaDeAtendimentoService.buscarProximoDaFilaDeAtendimento();
    }
}
