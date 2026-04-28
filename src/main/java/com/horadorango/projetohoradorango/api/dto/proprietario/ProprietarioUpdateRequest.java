package com.horadorango.projetohoradorango.api.dto.proprietario;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioUpdateRequest {

    @NotBlank
    @Length(max = 60)
    private String nome;

}
