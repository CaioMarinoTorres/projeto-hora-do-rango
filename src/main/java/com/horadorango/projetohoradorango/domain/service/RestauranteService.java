package com.horadorango.projetohoradorango.domain.service;

import com.horadorango.projetohoradorango.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestauranteService {

    private final RestauranteRepository repository;

}
