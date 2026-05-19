package com.horadorango.projetohoradorango.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum PerfilCobaloradorEnum {

    PROPRIETARIO(1, "Proprietário"),
    GERENTE     (2, "Gerente"     ),
    FUNCIONARIO (3, "Funcionário" );

    private final int codigo;
    private final String descricao;

    public static PerfilCobaloradorEnum porValor(int valor) {
        return Stream.of(PerfilCobaloradorEnum.values())
                .filter(t -> Objects.equals(valor, t.getCodigo()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Valor Enum invalido: " + valor));
    }

}
