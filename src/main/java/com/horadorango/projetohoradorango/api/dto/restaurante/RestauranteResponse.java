package com.horadorango.projetohoradorango.api.dto.restaurante;

import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteResponse {

    private Long id;

    private String nome;

    private String cnpj;

    private ProprietarioResponse proprietario;

}
