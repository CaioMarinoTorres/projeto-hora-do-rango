package com.horadorango.projetohoradorango.api.dto.proprietario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioResponse {

    private Long id;

    private String cpf;

    private String nome;

}
