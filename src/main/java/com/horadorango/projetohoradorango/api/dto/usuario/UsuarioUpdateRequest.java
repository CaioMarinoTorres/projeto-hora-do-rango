package com.horadorango.projetohoradorango.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateRequest {

    @NotBlank
    @Length(max = 60)
    private String nome;

    @NotBlank
    @Length(max = 11)
    private String telefone;

}
