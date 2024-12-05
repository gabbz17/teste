package com.example.Estoque.controller;

import com.example.Estoque.entity.Estoque;
import com.example.Estoque.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Estoque", description = "CRUD de Estoque")
@Slf4j
@RestController
@RequestMapping("api/v1/estoque")
public class EstoqueController {

    public final EstoqueService service;
    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @Operation(summary = "Criar um novo Produto", description = "Http para criar um novo Produto",
            responses = {@ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estoque.class))),
                    @ApiResponse(responseCode = "409", description = "Produto já cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PostMapping
    public ResponseEntity<Estoque> create(@RequestBody Estoque estoque){
        log.info("Solicitando criação do novo produto: " + estoque.toString());
        Estoque est = service.create(estoque);
        return ResponseEntity.status(201).body(est);
    }


    @Operation(summary = "Listar todos os Produtos", description = "Http para listar todos os Produtos",
            responses = {@ApiResponse(responseCode = "200", description = "Produtos listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estoque.class))),
                    @ApiResponse(responseCode = "404", description = "Lista não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping
    public ResponseEntity<List<Estoque>> findAll(){
        log.info("Solicitando lista de produtos");
        List<Estoque> estoques = service.findAll();
        return ResponseEntity.ok().body(estoques);
    }



    @Operation(summary = "Buscar produto pelo nome", description = "Http para buscar produto pelo nome",
        responses = {@ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estoque.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Estoque> findByName(@PathVariable("nome") String nome){
        log.info("Solicitando busca do produto pelo nome: " + nome);
        Estoque estoque = service.findByName(nome);
        return ResponseEntity.ok().body(estoque);
    }


    @Operation(summary = "Dar baixa no produto pelo nome", description = "Http para dar baixa no produto pelo nome",
            responses = {@ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estoque.class))),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/baixa/{nome}")
    public ResponseEntity<Estoque> buyByName(@PathVariable("nome") String nome){
        log.info("Solicitando baixa do produto pelo nome: " + nome);
        Estoque estoque = service.buyByName(nome);
        return ResponseEntity.ok().body(estoque);
    }


    @Operation(summary = "Buscar produto pelo id", description = "Http para buscar produto pelo id",
            responses = {@ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estoque.class))),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/id/{id}")
    public ResponseEntity<Estoque> findById(@PathVariable Long id){
        log.info("Solicitando busca do produto pelo id: " + id.toString());
        Estoque estoque = service.findById(id);
        return ResponseEntity.ok().body(estoque);
    }


    @Operation(summary = "Deletar o produto por id", description = "Http para deletar o produto por id",
        responses = {@ApiResponse(responseCode = "200", description = "Produto apagado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estoque.class))),
            @ApiResponse(responseCode = "204", description = "Usuario nao encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        log.info("Solicitando deleção do produto pelo id: " + id.toString());
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
