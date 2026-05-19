package com.horadorango.projetohoradorango.api.converter;

import com.horadorango.projetohoradorango.api.dto.usuario.UsuarioRequest;
import com.horadorango.projetohoradorango.api.dto.usuario.UsuarioResponse;
import com.horadorango.projetohoradorango.api.dto.usuario.UsuarioUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProprietarioConverter {

    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cpf", ignore = true)
    Usuario toEntity(UsuarioUpdateRequest request);

    Usuario ResponseToEntity(UsuarioResponse response);

    UsuarioResponse toResponse(Usuario entity);

    List<UsuarioResponse> toResponse(List<Usuario> entities);
}
