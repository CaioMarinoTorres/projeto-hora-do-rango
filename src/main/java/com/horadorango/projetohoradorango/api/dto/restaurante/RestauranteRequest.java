package com.horadorango.projetohoradorango.api.dto.restaurante;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RestauranteRequest extends RestauranteUpdateRequest{

    @CNPJ
    @NotBlank
    private String cnpj;

}