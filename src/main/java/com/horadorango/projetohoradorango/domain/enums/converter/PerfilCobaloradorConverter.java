package com.horadorango.projetohoradorango.domain.enums.converter;

import com.horadorango.projetohoradorango.domain.enums.PerfilCobaloradorEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter( autoApply = true)
public class PerfilCobaloradorConverter implements AttributeConverter<PerfilCobaloradorEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PerfilCobaloradorEnum value) {
        return Objects.isNull(value) ? null : value.getCodigo();
    }

    @Override
    public PerfilCobaloradorEnum convertToEntityAttribute(Integer integer) {
        return Objects.isNull(integer) ? null : PerfilCobaloradorEnum.porValor(integer);
    }
}
