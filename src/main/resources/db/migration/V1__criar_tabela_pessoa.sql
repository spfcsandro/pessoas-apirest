CREATE SEQUENCE pessoa_id_seq AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE HIBERNATE_SEQUENCE AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE TABLE tb_pessoa(
  id BIGINT GENERATED BY DEFAULT AS SEQUENCE pessoa_id_seq PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  rua VARCHAR(255),
  numero VARCHAR(255),
  bairro VARCHAR(255),
  cidade VARCHAR(255),
  estado VARCHAR(255),
  celular VARCHAR(255),
  telefone VARCHAR(255)
  
);