package com.cielo.clientservice.clientsservice.controllers.swagger;

import com.cielo.clientservice.clientsservice.DTO.ClienteDTO;
import com.cielo.clientservice.clientsservice.entities.clientes.Cliente;
import com.cielo.clientservice.clientsservice.entities.clientes.PessoaFisica;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Fila de atendimento", description = "API de fila de atendimento dos clientes")
public interface FilaDeAtendimentoSwaggerController {

    @Operation(summary = "Buscar cliente", description = "Buscar próximo cliente da fila de atendimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "204", description = "Fila vazia", content = @Content(mediaType = "application/json"))
    })
    ClienteDTO buscarFilaDeAtendimento();
}
