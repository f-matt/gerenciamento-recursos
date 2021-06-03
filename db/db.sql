CREATE DATABASE db_agendamento;

CREATE TABLE categorias (
	id SERIAL,
	descricao VARCHAR (128),
	ativa BOOLEAN,
	PRIMARY KEY (id)
);

CREATE TABLE recursos (
	id SERIAL,
	descricao VARCHAR (128),
	ativa BOOLEAN,
	categoria_id INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (categoria_id) 
		REFERENCES categorias (id) 
);

CREATE TABLE itens (
	id SERIAL,
	descricao VARCHAR (128),
	ativo BOOLEAN,
	recurso_id INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (recurso_id)
		REFERENCES recursos (id)
);

-- Dados de teste
INSERT INTO categorias (descricao, ativa)
VALUES
('Materiais de Informática', TRUE),
('Audiovisual', TRUE);

INSERT INTO recursos (descricao, ativa, categoria_id)
VALUES
('Notebook', TRUE, 1),
('Projetor', TRUE, 2),
('Caixa de som', TRUE, 2);
