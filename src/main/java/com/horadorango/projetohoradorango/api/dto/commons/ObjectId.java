package com.horadorango.projetohoradorango.api.dto.commons;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectId {

    @NotNull(message = "O ID é obrigatório")
    private Long id;

}
