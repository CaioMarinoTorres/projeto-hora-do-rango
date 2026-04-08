-- SEQUENCES

CREATE SEQUENCE IF NOT EXISTS tb_proprietario_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

-- TABELAS

DROP TABLE IF EXISTS tb_proprietario;
CREATE TABLE tb_proprietario
(
    id                   BIGINT         DEFAULT NEXTVAL ('tb_proprietario_seq'::regclass)   NOT NULL,
    nome                 VARCHAR (60)                                                       NOT NULL,
    cpf                  VARCHAR (11)                                                       NOT NULL,

    CONSTRAINT pk_tb_proprietario PRIMARY KEY   (id),
    CONSTRAINT uk_tb_proprietario UNIQUE        (cpf)
);