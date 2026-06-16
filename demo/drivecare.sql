
CREATE DATABASE IF NOT EXISTS drivecare_db;
USE drivecare_db;

TRUNCATE TABLE usuario;


INSERT INTO usuario (nome, email, senha, cpf_cnpj) 
VALUES ('João Victor Lessa', 'joao@email.com', '123', '123.456.789-00');

SELECT * FROM usuario;

CREATE TABLE IF NOT EXISTS veiculo (
    id_veiculo BIGINT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) NOT NULL,
    modelo VARCHAR(50),
    fabricante VARCHAR(50),
    id_usuario BIGINT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL 
);

CREATE TABLE veiculo (
    id_veiculo INT PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(10) UNIQUE NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    ano INT NOT NULL,
    km_atual INT NOT NULL,
    id_usuario INT,
    CONSTRAINT fk_usuario_veiculo FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

CREATE TABLE manutencao (
    id_manutencao INT PRIMARY KEY AUTO_INCREMENT,
    tipo_servico VARCHAR(100) NOT NULL,
    data_servico DATE NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    status_pagamento VARCHAR(20) DEFAULT 'Pendente',
    id_veiculo INT,
    CONSTRAINT fk_veiculo_manutencao FOREIGN KEY (id_veiculo) 
        REFERENCES veiculo(id_veiculo) ON DELETE CASCADE
);





