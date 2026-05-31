--
-- Standardized SQL Schema for Censo Nacional
--

CREATE DATABASE IF NOT EXISTS `7502523003_9_censo_nacional`;
USE `7502523003_9_censo_nacional`;

CREATE TABLE IF NOT EXISTS provincia (
  id_provincia int NOT NULL AUTO_INCREMENT,
  nombre_provincia varchar(100) NOT NULL,
  PRIMARY KEY (id_provincia)
);

INSERT INTO provincia VALUES (1,'Bolivar');

CREATE TABLE IF NOT EXISTS municipio (
  id_municipio int NOT NULL AUTO_INCREMENT,
  nombre_municipio varchar(100) NOT NULL,
  id_provincia int DEFAULT NULL,
  PRIMARY KEY (id_municipio),
  CONSTRAINT fk_municipio_provincia FOREIGN KEY (id_provincia) REFERENCES provincia (id_provincia) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO municipio VALUES (1,'Cartagena',1);

CREATE TABLE IF NOT EXISTS distrito (
  id_distrito int NOT NULL AUTO_INCREMENT,
  nombre_distrito varchar(100) NOT NULL,
  id_municipio int DEFAULT NULL,
  PRIMARY KEY (id_distrito),
  CONSTRAINT fk_distrito_municipio FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO distrito VALUES (1,'Localidad 1',1),(2,'Localidad 2',1);

CREATE TABLE IF NOT EXISTS vivienda (
  id_vivienda int NOT NULL AUTO_INCREMENT,
  direccion varchar(255) NOT NULL,
  tipo_vivienda enum('Casa','Apartamento','Indigena') NOT NULL,
  id_distrito int DEFAULT NULL,
  PRIMARY KEY (id_vivienda),
  CONSTRAINT fk_vivienda_distrito FOREIGN KEY (id_distrito) REFERENCES distrito (id_distrito) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO vivienda VALUES (1,'Sede Principal Norte','Casa',1),(2,'Apto 302','Apartamento',1),(3,'ZONA NORTE','Apartamento',1),(4,'Reserva Indigena A','Indigena',2),(5,'Avenida 5','Apartamento',1),(6,'Dir 1','Casa',1),(7,'Dir 2','Apartamento',1),(8,'Dir 3','Casa',1),(9,'Dir 4','Apartamento',1),(10,'Dir 5','Casa',1),(11,'Dir 6','Apartamento',1),(12,'Dir 7','Casa',1),(13,'Dir 8','Apartamento',1),(14,'Dir 9','Casa',1),(15,'ZONA SUR','Apartamento',1),(16,'Dir 11','Casa',1),(17,'Dir 12','Apartamento',1),(18,'Dir 13','Casa',1),(19,'Dir 14','Apartamento',1),(20,'Dir 15','Casa',1);

CREATE TABLE IF NOT EXISTS persona (
  id_persona int NOT NULL AUTO_INCREMENT,
  documento_identidad varchar(20) NOT NULL,
  primer_nombre varchar(50) NOT NULL,
  apellidos varchar(50) NOT NULL,
  fecha_nacimiento date NOT NULL,
  id_vivienda_censada int DEFAULT NULL,
  PRIMARY KEY (id_persona),
  UNIQUE (documento_identidad),
  CONSTRAINT fk_persona_vivienda FOREIGN KEY (id_vivienda_censada) REFERENCES vivienda (id_vivienda) ON DELETE RESTRICT ON UPDATE RESTRICT
);

INSERT INTO persona VALUES (1,'20001','Validado','Perez','2020-01-30',1),(3,'20003','Validado','Diferente','1994-05-30',2),(4,'20004','Validado','Diferente','1990-06-30',3),(5,'20005','Validado','Diferente','2020-04-26',4),(6,'30001','123456','Prueba','2000-05-15',1),(7,'30002','987654','Prueba','2000-05-15',1),(13,'70002','Test','Error','2010-12-23',1),(14,'70008','Test','Correcto','2023-06-21',1),(15,'12345678','Test','Test','2000-01-01',1);

CREATE TABLE IF NOT EXISTS matriculas (
  id int NOT NULL AUTO_INCREMENT,
  fecha datetime DEFAULT NULL,
  nombre varchar(100) DEFAULT NULL,
  apellidos varchar(100) DEFAULT NULL,
  asignatura varchar(100) DEFAULT NULL,
  nota1 decimal(3,1) DEFAULT NULL,
  nota2 decimal(3,1) DEFAULT NULL,
  nota3 decimal(3,1) DEFAULT NULL,
  nota_definitiva decimal(3,1) DEFAULT NULL,
  promedio decimal(3,1) DEFAULT NULL,
  mensaje text,
  PRIMARY KEY (id)
);

INSERT INTO matriculas VALUES (1,'2023-05-20 00:00:00','PERSONA 1','DE TAL','ESTRUCTURAS DE DATOS',1.0,NULL,NULL,0.2,1.0,'PRACTICAMENTE NO HIZO NADA'),(2,'2023-06-20 00:00:00','PERSONA 1','DE TAL','ESTRUCTURAS DE DATOS',1.0,1.0,NULL,0.4,1.0,'LA ACTIVIDAD ES UNA COPIA DE FRAGMENTOS DISPONIBLES EN INTERNET'),(3,'2023-07-20 00:00:00','PERSONA 1','DE TAL','ESTRUCTURAS DE DATOS',1.0,1.0,1.0,1.0,1.0,'NO DOMINA EL DESARROLLO'),(4,'2023-05-20 00:00:00','PERSONA 3','DE TAL','ESTRUCTURAS DE DATOS',4.0,NULL,NULL,0.8,4.0,'ACTIVIDAD AL 80%'),(5,'2023-06-20 00:00:00','PERSONA 3','DE TAL','ESTRUCTURAS DE DATOS',4.0,4.5,NULL,1.7,4.3,'ACTIVIDAD COMPLETA'),(6,'2023-07-20 00:00:00','PERSONA 3','DE TAL','ESTRUCTURAS DE DATOS',4.0,4.5,5.0,4.7,4.5,'ACTIVIDAD 100%');
