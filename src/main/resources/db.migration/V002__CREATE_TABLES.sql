-- SEQUENCES

CREATE SEQUENCE IF NOT EXISTS tb_item_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS tb_cardapio_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS tb_restaurante_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

-- TABELAS

DROP TABLE IF EXISTS tb_restaurante;
CREATE TABLE tb_restaurante
(
    id                      BIGINT DEFAULT NEXTVAL('tb_restaurante_seq'::regclass) NOT NULL,
    id_proprietario         BIGINT                                                 NOT NULL,
    nome                    VARCHAR(255),
    cnpj                    VARCHAR(255),

    CONSTRAINT pk_tb_restaurante    PRIMARY KEY (id),
    CONSTRAINT uk_tb_restaurante    UNIQUE      (cnpj),
    CONSTRAINT fk_tb_restaurante_0  FOREIGN KEY (id_proprietario) REFERENCES tb_proprietario (id)
);

DROP TABLE IF EXISTS tb_cardapio;
CREATE TABLE tb_cardapio
(
    id              BIGINT DEFAULT NEXTVAL('tb_cardapio_seq'::regclass) NOT NULL,
    id_restaurante  BIGINT                                              NOT NULL,
    nome            VARCHAR(255)                                        NOT NULL,

    CONSTRAINT pk_tb_cardapio   PRIMARY KEY (id),
    CONSTRAINT fk_tb_cardapio_0 FOREIGN KEY (id_restaurante) REFERENCES tb_restaurante (id)
);

DROP TABLE IF EXISTS tb_item;
CREATE TABLE tb_item
(
    id              BIGINT DEFAULT NEXTVAL('tb_item_seq'::regclass) NOT NULL,
    id_cardapio     BIGINT                                          NOT NULL,
    nome            VARCHAR(255)                                    NOT NULL,
    preco           DECIMAL(10,2)                                   NOT NULL,
    descricao       VARCHAR(255),

    CONSTRAINT pk_tb_item   PRIMARY KEY (id),
    CONSTRAINT fk_tb_item_0 FOREIGN KEY (id_cardapio) REFERENCES tb_cardapio (id)
);

