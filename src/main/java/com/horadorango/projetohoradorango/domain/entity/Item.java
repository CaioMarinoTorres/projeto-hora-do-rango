package com.horadorango.projetohoradorango.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Item.TABLE_NAME)
public class Item {

    public static final String TABLE_NAME = "tb_item";
    public static final String SEQ_NAME = "tb_item_seq";

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_item", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "descricao", nullable = false)
    private String descricao;

}
