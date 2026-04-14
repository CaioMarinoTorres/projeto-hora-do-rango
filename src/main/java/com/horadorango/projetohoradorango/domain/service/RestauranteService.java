package com.horadorango.projetohoradorango.domain.service;

import com.horadorango.projetohoradorango.api.converter.RestauranteConverter;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteRequest;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteResponse;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Proprietario;
import com.horadorango.projetohoradorango.domain.entity.Restaurante;
import com.horadorango.projetohoradorango.domain.exeption.DataNotFoundException;
import com.horadorango.projetohoradorango.domain.repository.RestauranteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository repository;
    private final RestauranteConverter converter;
    private final ProprietarioService proprietarioService;

    @Transactional
    public RestauranteResponse save(RestauranteRequest request){
        Proprietario proprietario = proprietarioService.findById(request.getProprietarioId());

        Restaurante restaurante = converter.toEntity(request);
        restaurante.setProprietario(proprietario);
        repository.save(restaurante);

        return converter.toResponse(restaurante);
    }

    @Transactional
    public RestauranteResponse update(RestauranteUpdateRequest request, Long id){
        Restaurante restaurante = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Restaurante não encontrado"));
        Restaurante updateEntity = converter.toEntity(request);

        if(restaurante.getProprietario().getId().equals(request.getProprietarioId())){
            restaurante.setNome(updateEntity.getNome());
        } else {
            Proprietario proprietario = proprietarioService.findById(request.getProprietarioId());
            restaurante.setNome(updateEntity.getNome());
            restaurante.setProprietario(proprietario);
        }

        restaurante = repository.save(restaurante);
        return converter.toResponse(restaurante);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    public RestauranteResponse findById(Long id){
        Restaurante restaurante = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Restaurante não encontrado"));
        return converter.toResponse(restaurante);
    }

    public List<RestauranteResponse> findAll(){
        return repository.findAll().stream().map(converter::toResponse).toList();
    }

}
