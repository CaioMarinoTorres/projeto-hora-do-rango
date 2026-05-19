package com.horadorango.projetohoradorango.domain.service;

import com.horadorango.projetohoradorango.api.converter.RestauranteConverter;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteRequest;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteResponse;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Usuario;
import com.horadorango.projetohoradorango.domain.entity.Restaurante;
import com.horadorango.projetohoradorango.domain.exeption.DataConflictException;
import com.horadorango.projetohoradorango.domain.exeption.DataNotFoundException;
import com.horadorango.projetohoradorango.domain.repository.RestauranteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository repository;
    private final RestauranteConverter converter;
    private final UsuarioService usuarioService;

    @Transactional
    public RestauranteResponse save(RestauranteRequest request) {
        log.info("[RestauranteService] [save] ---> Iniciando processo de persistência do registro de restaurante: {}", request);
        Restaurante restaurante = converter.toEntity(request);

        validarDuplicidade(restaurante);

        log.info("[RestauranteService] [save] ---> Buscando proprietário pelo ID: {}", request.getProprietario().getId());
        Usuario usuario = usuarioService.findById(request.getProprietario().getId());
        log.info("[RestauranteService] [save] ---> Proprietário encontrado com sucesso: {}", usuario);

        log.info("[RestauranteService] [save] ---> Persistindo registro de restaurante: {}", restaurante);
        repository.save(restaurante);

        log.info("[RestauranteService] [save] ---> Restaurante persistido com sucesso: {}", restaurante);
        return converter.toResponse(restaurante);
    }

    @Transactional
    public RestauranteResponse update(RestauranteUpdateRequest request, Long id) {
        Restaurante restaurante = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Restaurante não encontrado"));

        // TODO alterar lógica, inserir exception

        repository.save(restaurante);
        return converter.toResponse(restaurante);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public RestauranteResponse findById(Long id) {
        Restaurante restaurante = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Restaurante não encontrado"));
        return converter.toResponse(restaurante);
    }

    public List<RestauranteResponse> findAll() {
        log.info("[RestauranteService] [findAll] ---> Buscando todos os registros de restaurante");

        List<Restaurante> entities = repository.findAll();
        log.info("[RestauranteService] [findAll] ---> Foram encontrados {} registro de restaurante", entities.size());

        return converter.toResponse(entities);
    }

    private void validarDuplicidade(Restaurante restaurante) {
        log.info("[RestauranteService] [validarDuplicidade] ---> Validando duplicidade para CNPJ: {}", restaurante.getCnpj());
        repository.findByCnpj(restaurante.getCnpj()).ifPresent(restaurante1 -> {
            throw new DataConflictException(String.format("Já existe um restaurante cadastrado com esse CNPJ como o nome %s", restaurante1.getNome()));
        });
    }

}
