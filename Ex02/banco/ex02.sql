/* a. Crie uma tabela Pais com os atributos id (int autoincrement) , nome(varchar 100),
populacao (bigint) e area (number 15,2). Carregue alguns países. Consulte a Wikipedia para
obter as informações de população e área. */

create database if not exists ex02;

use ex02;

create table if not exists pais (
	id 		int auto_increment,
	nome		varchar(100),
	populacao	bigint,
	area		double(15,2),
	primary key(id)
	);

INSERT INTO pais VALUES (1, 'Alemanha', 256000, 2560.00);
INSERT INTO pais VALUES (2, 'Brasil',1200000, 756000);
INSERT INTO pais VALUES (3, 'Belgica', 160000, 500.000);
INSERT INTO pais VALUES (4, 'EUA', 800000, 98000.0);
INSERT INTO pais VALUES (5, 'Canada', 569200, 400000);	