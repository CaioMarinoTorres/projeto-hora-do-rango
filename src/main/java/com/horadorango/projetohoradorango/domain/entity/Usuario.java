package com.horadorango.projetohoradorango.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = Usuario.TABLE_NAME)
public class Usuario {

    public static final String TABLE_NAME = "tb_usuario";
    public static final String SEQ_NAME = "tb_usuario_seq";

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME )
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_usuario", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "senha", nullable = false , length = 60)
    private String senha;

    @Column(name = "email", nullable = false , length = 60)
    private String email;

    @Column(name = "telefone", nullable = false , length = 11)
    private String telefone;

}

