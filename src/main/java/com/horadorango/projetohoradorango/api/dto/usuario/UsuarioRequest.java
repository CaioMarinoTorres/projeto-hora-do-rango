package com.horadorango.projetohoradorango.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsuarioRequest extends UsuarioUpdateRequest {

    @NotBlank
    @Length(max = 60)
    private String email;

    @NotBlank
    @Length(max = 60)
    private String senha;

    @CPF
    @NotBlank
    private String cpf;

}
