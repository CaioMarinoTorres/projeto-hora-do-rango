package com.horadorango.projetohoradorango.api.controller;

import com.horadorango.projetohoradorango.api.converter.ProprietarioConverter;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioRequest;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioResponse;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Proprietario;
import com.horadorango.projetohoradorango.domain.repository.ProprietarioRepository;
import com.horadorango.projetohoradorango.domain.service.ProprietarioService;
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
    private final ProprietarioConverter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProprietarioResponse> save(@Valid @RequestBody ProprietarioRequest request) {
        Proprietario proprietario = converter.toEntity(request);
        Proprietario savedProprietario = service.save(proprietario);
        return ResponseEntity.ok(converter.toResponse(savedProprietario));
    }

    @GetMapping
    public List<Proprietario> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioResponse> findById(@PathVariable Long id){
        Proprietario entity = service.findById(id);
        return ResponseEntity.ok(converter.toResponse(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProprietarioResponse> update(@PathVariable Long id, @Valid @RequestBody ProprietarioUpdateRequest request){
        Proprietario entity = converter.toEntity(request);
        Proprietario updateEntity = service.update(entity, id);
        return ResponseEntity.ok(converter.toResponse(updateEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
       service.delete(id);
       return ResponseEntity.noContent().build();
    }
}