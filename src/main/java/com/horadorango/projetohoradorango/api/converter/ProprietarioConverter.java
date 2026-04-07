package com.horadorango.projetohoradorango.api.converter;

import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioRequest;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioResponse;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Proprietario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProprietarioConverter {

    Proprietario toEntity(ProprietarioRequest request);

    Proprietario toEntity(ProprietarioUpdateRequest request);

    ProprietarioResponse toResponse(Proprietario entity);

}
