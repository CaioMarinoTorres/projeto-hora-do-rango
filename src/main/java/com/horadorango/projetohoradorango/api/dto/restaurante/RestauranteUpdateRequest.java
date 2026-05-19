package com.horadorango.projetohoradorango.api.dto.restaurante;

import com.horadorango.projetohoradorango.api.dto.commons.ObjectId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteUpdateRequest {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private ObjectId proprietario;

}
