package com.horadorango.projetohoradorango.api.controller;

import com.horadorango.projetohoradorango.api.converter.ProprietarioConverter;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioRequest;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioResponse;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Proprietario;
import com.horadorango.projetohoradorango.domain.service.ProprietarioService;
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
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final ProprietarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salva um novo proprietário", description = "Cria um novo proprietário com base nos dados fornecidos.")
    public ResponseEntity<ProprietarioResponse> save(@Valid @RequestBody ProprietarioRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    @Operation(summary = "Lista todos os proprietários", description = "Retorna uma lista com todos os proprietários cadastrados.")
    public List<Proprietario> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um proprietário por ID", description = "Retorna os detalhes de um proprietário com base no ID fornecido.")
    public ResponseEntity<ProprietarioResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findByIdResponse(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um proprietário existente", description = "Atualiza os detalhes de um proprietário com base no ID fornecido e nos dados fornecidos.")
    public ResponseEntity<ProprietarioResponse> update(@PathVariable Long id, @Valid @RequestBody ProprietarioUpdateRequest request){
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um proprietário", description = "Exclui um proprietário com base no ID fornecido.")
    public ResponseEntity<Void> delete(@PathVariable Long id){
       service.delete(id);
       return ResponseEntity.noContent().build();
    }
}