package com.horadorango.projetohoradorango.domain.service;

import com.horadorango.projetohoradorango.api.converter.ProprietarioConverter;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioRequest;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioResponse;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Proprietario;
import com.horadorango.projetohoradorango.domain.exeption.DataConflictException;
import com.horadorango.projetohoradorango.domain.exeption.DataNotFoundException;
import com.horadorango.projetohoradorango.domain.repository.ProprietarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProprietarioService {

    private final ProprietarioRepository repository;
    private final ProprietarioConverter converter;

    @Transactional
    public ProprietarioResponse save(ProprietarioRequest request) {
        Proprietario proprietario = converter.toEntity(request);
        if (cadastroJaExiste(proprietario)){
            throw new DataConflictException("Este CPF já está cadastrado");
        }
        repository.save(proprietario);
        return converter.toResponse(proprietario);
    }

    @Transactional
    public ProprietarioResponse update(ProprietarioUpdateRequest request, Long id){
        Proprietario proprietario = findById(id);
        proprietario.setNome(request.getNome());
        repository.save(proprietario);
        return converter.toResponse(proprietario);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        repository.deleteById(id);
    }

    public List<Proprietario> findAll(){
        return repository.findAll();
    }

    public ProprietarioResponse findByIdResponse(Long id){
        return converter.toResponse(findById(id));
    }

    protected Proprietario findById(Long id){
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Proprietário não encontrado"));
    }

    private Boolean cadastroJaExiste(Proprietario proprietario){
        return repository.findAllByCpf(proprietario.getCpf())
                .filter(p -> !p.equals(proprietario))
                .isPresent();
    }

}

