package com.horadorango.projetohoradorango.api.converter;

import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteRequest;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteResponse;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestauranteConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "proprietario", ignore = true)
    Restaurante toEntity(RestauranteRequest request);

    @Mapping(target = "cnpj", ignore = true)
    Restaurante toEntity(RestauranteUpdateRequest request);

    RestauranteResponse toResponse(Restaurante entity);

}
