package com.horadorango.projetohoradorango.domain.service;

import com.horadorango.projetohoradorango.domain.entity.Proprietario;
import com.horadorango.projetohoradorango.domain.exeption.DataConflictExeption;
import com.horadorango.projetohoradorango.domain.repository.ProprietarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProprietarioService {

    private final ProprietarioRepository repository;

    @Transactional
    public Proprietario save(Proprietario proprietario) {
        if (cadastroJaExiste(proprietario)){
            throw new DataConflictExeption("Este CPF já está cadastrado");
        }
        return repository.save(proprietario);
    }

    @Transactional
    public List<Proprietario> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Proprietario update(Proprietario proprietario, Long id){
        Proprietario entity = findById(id);
        entity.setNome(proprietario.getNome());
        return repository.save(entity);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        repository.deleteById(id);
    }

    public Proprietario findById(Long id){
        return repository.findById(id).orElseThrow(() -> new DataConflictExeption("Proprietário não encontrado"));
    }

    private Boolean cadastroJaExiste(Proprietario proprietario){
        return repository.findAllByCpf(proprietario.getCpf())
                .filter(p -> !p.equals(proprietario))
                .isPresent();
    }

}

