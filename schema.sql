CREATE DATABASE IF NOT EXISTS 7502510025_1_app_ofertas;
USE 7502510025_1_app_ofertas;

CREATE TABLE IF NOT EXISTS subastas (
                                        id_subasta INT AUTO_INCREMENT PRIMARY KEY,
                                        id_articulo INT NOT NULL,
                                        precio_inicial DECIMAL(10, 2) NOT NULL,
    precio_actual DECIMAL(10, 2) NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_limite DATETIME NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVA'
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ofertas (
                                       id_oferta INT AUTO_INCREMENT PRIMARY KEY,
                                       id_subasta INT NOT NULL,
                                       id_usuario_ofertante INT NOT NULL,
                                       monto DECIMAL(10, 2) NOT NULL,
    fecha_hora DATETIME NOT NULL,
    FOREIGN KEY (id_subasta) REFERENCES subastas (id_subasta) ON DELETE CASCADE
    ) ENGINE=InnoDB;