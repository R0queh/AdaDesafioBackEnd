package com.cielo.clientservice.clientsservice.controllers.swagger;

import com.cielo.clientservice.clientsservice.entities.clientes.PessoaJuridica;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Company", description = "Cielo company API")
public interface PessoaJuridicaSwaggerController {

    @Operation(summary = "Buscar pessoa jurídica", description = "Buscar pessoa jurídica pelo CNPJ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa jurídica encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PessoaJuridica.class))),
            @ApiResponse(responseCode = "404", description = "Pessoa jurídica não encontrada", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<PessoaJuridica> buscarPessoaJuridica(@Parameter(description = "CNPJ da pessoa jurídica") String cnpj);

    @Operation(summary = "Deletar pessoa jurídica", description = "Deletar pessoa jurídica pelo CNPJ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa jurídica deleteda", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "Pessoa jurídica deleteda com Sucesso"))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<String> deletarPessoaJuridica(@Parameter(description = "CNPJ da pessoa jurídica") final String cnpj);

    @Operation(summary = "cadastrar pessoa jurídica", description = "Atualizar pessoa jurídica conforme objeto recebido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa jurídica criada", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "Pessoa jurídica criada com sucesso"))),
            @ApiResponse(responseCode = "400", description = "Objeto com parâmetros invalidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Conflito no cadastro de pessoa física, pessoa jurídica já existe", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<String> cadastrarPessoaJuridica(@Parameter(description = "Objeto com as informações da pessoa jurídica") PessoaJuridica pessoaJuridica);

    @Operation(summary = "Update company", description = "Atualizar pessoa jurídica conforme objeto recebido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa jurídica atualizada",  content = @Content(mediaType = "application/json",schema = @Schema(implementation = PessoaJuridica.class))),
            @ApiResponse(responseCode = "400", description = "Objeto com parâmetros invalidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Conflito no cadastro de pessoa jurídica, pessoa não encontrada", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<PessoaJuridica> atualizarPessoaJuridica(@Parameter(description = "Objeto com as informações da pessoa jurídica") PessoaJuridica pessoaJuridica);
}
