drop database if exists cadcliente;

create database cadcliente;

use cadcliente;

create table cliente(
	dtcadastro date,
	nome varchar(150),
	email varchar(150) primary key,
	cpf varchar(14),	
	telefone varchar(16),
	dtnasc date,
	sexo enum('M','F'),
	logradouro varchar(200),
	numero int,
	cep varchar(15),
	complemento varchar(200),
	cidade varchar(60),
	uf varchar(2),
	observacoes varchar(200)	
);