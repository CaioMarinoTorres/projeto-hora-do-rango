package com.horadorango.projetohoradorango.domain.entity;

import com.horadorango.projetohoradorango.domain.enums.PerfilCobaloradorEnum;
import com.horadorango.projetohoradorango.domain.enums.converter.PerfilCobaloradorConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = Colaborador.TABLE_NAME)
public class Colaborador {

    public static final String TABLE_NAME = "tb_colaborador";
    public static final String SEQ_NAME = "tb_colaborador_seq";

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME )
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_colaborador", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "perfil", nullable = false)
    private PerfilCobaloradorEnum perfil;

}