package com.horadorango.projetohoradorango.domain.service;

import com.horadorango.projetohoradorango.api.converter.ProprietarioConverter;
import com.horadorango.projetohoradorango.api.dto.usuario.UsuarioRequest;
import com.horadorango.projetohoradorango.api.dto.usuario.UsuarioResponse;
import com.horadorango.projetohoradorango.api.dto.usuario.UsuarioUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Usuario;
import com.horadorango.projetohoradorango.domain.exeption.DataConflictException;
import com.horadorango.projetohoradorango.domain.exeption.DataNotFoundException;
import com.horadorango.projetohoradorango.domain.repository.ProprietarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final ProprietarioRepository repository;
    private final ProprietarioConverter converter;

    @Transactional
    public UsuarioResponse save(UsuarioRequest request) {
        log.info("[UsuarioService] [save] ---> Iniciando o processo de persistência de usuario: {}", request);
        Usuario usuario = converter.toEntity(request);

        validarDuplicidade(usuario);

        log.info("[UsuarioService] [save] ---> Persistido o registro de usuario: {}", usuario);
        Usuario usuarioSaved = repository.save(usuario);

        log.info("[UsuarioService] [save] ---> Usuario persistido com sucesso: {}", usuarioSaved);
        return converter.toResponse(usuarioSaved);
    }

    @Transactional
    public UsuarioResponse update(UsuarioUpdateRequest request, Long id){
        Usuario usuario = findById(id);
        usuario.setNome(request.getNome());
        Usuario propUpdate = repository.save(usuario);
        return converter.toResponse(propUpdate);
    }

    @Transactional
    public void delete(Long id){
        repository.delete(findById(id));
    }

    public List<UsuarioResponse> findAll(){
        return converter.toResponse(repository.findAll());
    }

    public UsuarioResponse findByIdResponse(Long id){
        return converter.toResponse(findById(id));
    }

    protected Usuario findById(Long id){
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Proprietário não encontrado"));
    }

    private void validarDuplicidade(Usuario usuario){
        log.info("[UsuarioService] [validarDuplicidade] ---> Validando duplicidade para CPF: {}", usuario.getCpf());
        repository.findByCpf(usuario.getCpf()).ifPresent(usuario1 -> {
            throw new DataConflictException(String.format("O CPF %s já está cadastrado", usuario1.getCpf()));
        });
    }

}

