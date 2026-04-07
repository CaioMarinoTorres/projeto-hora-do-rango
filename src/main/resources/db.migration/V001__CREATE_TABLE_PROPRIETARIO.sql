create table proprietario
(
    id_proprietario      BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    nome                 VARCHAR (60),
    cpf                  VARCHAR (11),
    PRIMARY KEY (id_proprietario),
    UNIQUE (cpf)
);