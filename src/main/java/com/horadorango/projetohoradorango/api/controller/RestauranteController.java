package com.horadorango.projetohoradorango.api.controller;

import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteRequest;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteResponse;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteUpdateRequest;
import com.horadorango.projetohoradorango.domain.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra um novo restaurante", description = "Cadastra um novo restaurante com base nos dados fornecidos.")
    public ResponseEntity<RestauranteResponse> save(@Valid @RequestBody RestauranteRequest request){
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um restaurante por ID", description = "Retorna os detalhes de um restaurante com base no ID fornecido.")
    public ResponseEntity<RestauranteResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lista todos os restaurantes", description = "Retorna uma lista com todos os restaurantes cadastrados.")
    public List<RestauranteResponse> findAll(){
        return service.findAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um restaurante existente", description = "Atualiza os detalhes de um restaurante com base no ID fornecido e nos dados fornecidos.")
    public ResponseEntity<RestauranteResponse> update(@Valid @RequestBody RestauranteUpdateRequest request, @PathVariable Long id){
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um restaurante", description = "Exclui um restaurante com base no ID fornecido.")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}