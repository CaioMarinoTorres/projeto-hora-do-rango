-- SEQUENCES

CREATE SEQUENCE IF NOT EXISTS tb_usuario_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

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

DROP TABLE IF EXISTS tb_usuario;
CREATE TABLE tb_usuario
(
    id_usuario BIGINT       DEFAULT NEXTVAL ('tb_usuario_seq'::regclass) NOT NULL,
    nome       VARCHAR (60)                                              NOT NULL,
    cpf        VARCHAR (11)                                              NOT NULL,
    senha      VARCHAR (60)                                              NOT NULL,
    email      VARCHAR (60)                                              NOT NULL,
    telefone   VARCHAR (11)                                              NOT NULL,

    CONSTRAINT pk_tb_usuario PRIMARY KEY (id_usuario),
    CONSTRAINT uk_tb_usuario UNIQUE      (cpf),
    CONSTRAINT uk_tb_email   UNIQUE      (email)
);

DROP TABLE IF EXISTS tb_colaborador;
CREATE TABLE tb_colaborador
(
    id_colaborador BIGINT DEFAULT NEXTVAL('tb_restaurante_seq'::regclass) NOT NULL,
    id_usuario     BIGINT                                                 NOT NULL,
    perfil         INT                                                    NOT NULL,

    CONSTRAINT pk_tb_colaborador   PRIMARY KEY (id_colaborador),
    CONSTRAINT fk_tb_colaborador_0 FOREIGN KEY (id_usuario) REFERENCES tb_usuario (id_usuario)
);

DROP TABLE IF EXISTS tb_restaurante;
CREATE TABLE tb_restaurante
(
    id_restaurante  BIGINT DEFAULT NEXTVAL('tb_restaurante_seq'::regclass) NOT NULL,
    id_colaborador  BIGINT                                                 NOT NULL,
    nome            VARCHAR(255)                                           NOT NULL,
    cnpj            VARCHAR(255)                                           NOT NULL,

    CONSTRAINT pk_tb_restaurante    PRIMARY KEY (id_restaurante),
    CONSTRAINT uk_tb_restaurante    UNIQUE      (cnpj),
    CONSTRAINT fk_tb_restaurante_0  FOREIGN KEY (id_colaborador) REFERENCES tb_colaborador (id_colaborador)
);

DROP TABLE IF EXISTS tb_cardapio;
CREATE TABLE tb_cardapio
(
    id_cardapio     BIGINT DEFAULT NEXTVAL('tb_cardapio_seq'::regclass) NOT NULL,
    id_restaurante  BIGINT                                              NOT NULL,
    nome            VARCHAR(255)                                        NOT NULL,

    CONSTRAINT pk_tb_cardapio   PRIMARY KEY (id_cardapio),
    CONSTRAINT fk_tb_cardapio_0 FOREIGN KEY (id_restaurante) REFERENCES tb_restaurante (id_restaurante)
);

DROP TABLE IF EXISTS tb_item;
CREATE TABLE tb_item
(
    id_item              BIGINT DEFAULT NEXTVAL('tb_item_seq'::regclass) NOT NULL,
    id_cardapio     BIGINT                                          NOT NULL,
    nome            VARCHAR(255)                                    NOT NULL,
    preco           DECIMAL(10,2)                                   NOT NULL,
    descricao       VARCHAR(255),

    CONSTRAINT pk_tb_item   PRIMARY KEY (id_item),
    CONSTRAINT fk_tb_item_0 FOREIGN KEY (id_cardapio) REFERENCES tb_cardapio (id_cardapio)
);
