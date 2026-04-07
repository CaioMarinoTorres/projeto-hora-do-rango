package com.horadorango.projetohoradorango.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurante {

    @Id
    private Long id;

    private String nome;

    private Integer cnpj;

}
