package com.horadorango.projetohoradorango.api.dto.restaurante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteUpdateRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Long proprietarioId;

}
