package com.horadorango.projetohoradorango.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = Proprietario.TABLE_NAME)
public class Proprietario {

    public static final String TABLE_NAME = "tb_proprietario";
    public static final String SEQ_NAME = "tb_proprietario_seq";

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME )
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

}
