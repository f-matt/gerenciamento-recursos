-- CREATE DATABASE agendamento;

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
	disponivel BOOLEAN,
	recurso_id INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (recurso_id)
		REFERENCES recursos (id)
);

CREATE TABLE status_solicitacao (
	id SERIAL,
	descricao VARCHAR (32) NOT NULL UNIQUE,
	PRIMARY KEY (id)
);

CREATE TABLE solicitacoes (
	id SERIAL,
	dthr_solicitacao TIMESTAMP NOT NULL DEFAULT NOW(),
	dthr_devolucao TIMESTAMP,
	recurso_id INTEGER,
	item_id INTEGER,
	status_id INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (recurso_id) REFERENCES recursos (id),
	FOREIGN KEY (item_id) REFERENCES itens (id),
	FOREIGN KEY (status_id) REFERENCES status_solicitacao (id) 
);

CREATE TABLE perfis (
	id SERIAL,
	nome VARCHAR(32) UNIQUE, 
	PRIMARY KEY (id)
);


CREATE TABLE usuarios (
	id SERIAL,
	nome VARCHAR(128),
	login VARCHAR(32) UNIQUE,
	password VARCHAR(256),
	ativo BOOLEAN,
	perfil_id INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (perfil_id) REFERENCES perfis (id)
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

INSERT INTO status_solicitacao (descricao)
VALUES
('REGISTRADA'),
('ATENDIDA'),
('CONCLUÍDA'),
('CANCELADA');

INSERT INTO perfis (nome)
VALUES
('ADMINISTRADOR'),
('OPERADOR'),
('SOLICITANTE');
