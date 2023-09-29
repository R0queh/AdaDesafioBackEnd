package com.cielo.clientservice.clientsservice.controllers.swagger;

import com.cielo.clientservice.clientsservice.entities.clientes.PessoaFisica;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Pessoa física", description = "API de pessoa física CIELO")
public interface PessoaFisicaSwaggerController {

    @Operation(summary = "Buscar pessoa física", description = "Buscar pessoa física por cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa física encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PessoaFisica.class))),
            @ApiResponse(responseCode = "404", description = "Pessoa física não encontrada", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<PessoaFisica> buscarPessoaFisica(@Parameter(description = "CPF da pessoa física") String cpf);

    @Operation(summary = "Deletar pessoa física", description = "Deletar pessoa fisica por CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa física deletada", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "Pessoa física deletada com sucesso"))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<String> deletarPessoaFisica(@Parameter(description = "The person cpf code") final String cpf);

    @Operation(summary = "Cadastrar pessoa física", description = "Cadastrar pessoa física conforme objeto recebido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa física criada", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "Pessoa física criada com sucesso"))),
            @ApiResponse(responseCode = "400", description = "Objeto com parâmetros invalidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Conflito no cadastro de pessoa física, pessoa física já existe", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<String> cadastroPessoaFisica(@Parameter(description = "Objeto com as informações da pessoa física") PessoaFisica pessoaFisica);

    @Operation(summary = "Atualização de pessoa física", description = "Atualizar pessoa fisica conforme objeto recebido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa física atualizada", content = @Content(mediaType = "application/json",schema = @Schema(implementation = PessoaFisica.class))),
            @ApiResponse(responseCode = "400", description = "Objeto com parâmetros invalidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Conflito no cadastro de pessoa física, pessoa não encontrada", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<PessoaFisica> atualizarPessoaFisica(@Parameter(description = "Objeto com as informações da pessoa física") PessoaFisica pessoaFisica);
}
