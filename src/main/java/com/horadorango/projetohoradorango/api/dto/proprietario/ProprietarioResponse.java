package com.horadorango.projetohoradorango.api.dto.proprietario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioResponse {

    private Long id;

    private String cpf;

    private String nome;

}
