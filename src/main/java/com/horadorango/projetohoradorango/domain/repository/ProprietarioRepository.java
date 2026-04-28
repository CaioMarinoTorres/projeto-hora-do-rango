package com.horadorango.projetohoradorango.domain.repository;

import com.horadorango.projetohoradorango.domain.entity.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    Optional<Proprietario> findByCpf(String cpf);

}
