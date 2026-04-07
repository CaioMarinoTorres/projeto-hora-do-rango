package com.horadorango.projetohoradorango.api.dto.proprietario;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioUpdateRequest {

    @NotBlank
    @Length(max = 60)
    private String nome;

}
