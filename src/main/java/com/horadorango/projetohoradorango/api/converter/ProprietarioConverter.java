package com.horadorango.projetohoradorango.api.converter;

import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioRequest;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioResponse;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Proprietario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProprietarioConverter {

    @Mapping(target = "id", ignore = true)
    Proprietario toEntity(ProprietarioRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cpf", ignore = true)
    Proprietario toEntity(ProprietarioUpdateRequest request);

    Proprietario ResponseToEntity(ProprietarioResponse response);

    ProprietarioResponse toResponse(Proprietario entity);

    List<ProprietarioResponse> toResponse(List<Proprietario> entities);
}
