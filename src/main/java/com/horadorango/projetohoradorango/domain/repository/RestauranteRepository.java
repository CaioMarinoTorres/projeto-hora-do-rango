package com.horadorango.projetohoradorango.domain.repository;

import com.horadorango.projetohoradorango.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    Optional<Restaurante> findByCnpj(String cnpj);

}
