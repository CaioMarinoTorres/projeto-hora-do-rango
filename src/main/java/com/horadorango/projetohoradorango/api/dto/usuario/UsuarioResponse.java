package com.horadorango.projetohoradorango.api.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Long id;

    private String cpf;

    private String nome;

    private String telefone;

    private String email;

}
