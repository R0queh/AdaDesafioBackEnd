package com.cielo.clientservice.clientsservice.controllers.v2;

import com.cielo.clientservice.clientsservice.DTO.ClienteDTO;
import com.cielo.clientservice.clientsservice.DTO.v2.ClienteDTOV2;
import com.cielo.clientservice.clientsservice.controllers.swagger.v1.FilaDeAtendimentoSwaggerController;
import com.cielo.clientservice.clientsservice.controllers.swagger.v2.FilaDeAtendimentoV2SwaggerController;
import com.cielo.clientservice.clientsservice.services.v2.FilaDeAtendimentoV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/filaDeAtendimento")
public class FilaDeAtendimentoV2Controller implements FilaDeAtendimentoV2SwaggerController {

    final FilaDeAtendimentoV2Service filaDeAtendimentoService;

    @Autowired
    public FilaDeAtendimentoV2Controller(FilaDeAtendimentoV2Service filaDeAtendimentoService) {
        this.filaDeAtendimentoService = filaDeAtendimentoService;
    }

    @Override
    @GetMapping
    public ClienteDTOV2 buscarFilaDeAtendimento(){
        return filaDeAtendimentoService.buscarProximoDaFilaDeAtendimento();
    }
}
