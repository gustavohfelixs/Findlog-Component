CREATE DATABASE findlog;

use findlog;

CREATE TABLE estabelecimento (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    nome_estab TEXT NOT NULL,
    endereco_rua TEXT NOT NULL,
    endereco_cep TEXT NOT NULL,
    endereco_numero TEXT NOT NULL,
    endereco_bairro TEXT NOT NULL,
    endereco_estado TEXT NOT NULL,
    endereco_pais TEXT NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO estabelecimento VALUES 
(null, "Carrefour São Caetano do Sul", "Aquidaban", "09520180", "s/n", "Fundação", "São Paulo", "Brasil");

CREATE TABLE usuario (
    id INT NOT NULL AUTO_INCREMENT,
    nome TEXT NOT NULL,
    email TEXT NOT NULL,
    senha TEXT NOT NULL,
    role TEXT NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO usuario values 
(null, "Gustavo", "gustavo.carrefour@gmail.com", "password", "ADMIN");

CREATE TABLE usuario_estabelecimento (
    id_usuario INT NOT NULL,
    id_estab INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_estab) REFERENCES estabelecimento(id)
);

INSERT INTO usuario_estabelecimento values
(1, 1);

CREATE TABLE estoque (
    id INT NOT NULL  AUTO_INCREMENT,
    id_estab INT NOT NULL,
    nome TEXT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_estab) REFERENCES estabelecimento(id)
);

INSERT INTO estoque values 
(null, 1, "UAT");


CREATE TABLE produtos (
    id INT NOT NULL AUTO_INCREMENT, 
    nome TEXT NOT NULL,
    valor decimal NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO produtos values 
(null, "Shampoo Careca", 12.00),
(null, "Cheetos Boyah", 5.00);

CREATE TABLE estoque_produtos (
    id_produto INT NOT NULL,
    id_estoque INT NOT NULL,
    qtd_produto decimal NOT NULL,
    un_medida TEXT NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produtos(id),
    FOREIGN KEY (id_estoque) REFERENCES estoque(id)
);

INSERT INTO estoque_produtos values 
(1, 1, 10.0, "PC"),
(1, 1, 2.0, "KG");
