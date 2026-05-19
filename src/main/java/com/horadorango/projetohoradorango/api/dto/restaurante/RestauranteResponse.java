package com.horadorango.projetohoradorango.api.dto.restaurante;

import com.horadorango.projetohoradorango.api.dto.usuario.UsuarioResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteResponse {

    private Long id;

    private String nome;

    private String cnpj;

}
