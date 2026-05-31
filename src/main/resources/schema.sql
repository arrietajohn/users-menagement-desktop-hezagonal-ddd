-- =============================================
-- Script de creación de la base de datos
-- Gestión de Usuarios - Arquitectura Hexagonal
-- =============================================

CREATE DATABASE IF NOT EXISTS crud_usuarios
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE crud_usuarios;

CREATE TABLE IF NOT EXISTS users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        ENUM('ADMIN', 'MEMBER', 'REVIEWER') NOT NULL,
    status      ENUM('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED') NOT NULL DEFAULT 'PENDING',
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
-- Módulo: Gestión de Proyectos Empresariales
-- Ejercicio 1 - Arquitectura Hexagonal + DDD
-- =============================================

CREATE TABLE IF NOT EXISTS proyecto (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_clave  VARCHAR(50)  NOT NULL UNIQUE,
    denominacion  VARCHAR(150) NOT NULL,
    fecha_inicio  DATE         NOT NULL,
    fecha_fin     DATE,
    estado        ENUM('PLANIFICADO','EN_CURSO','PAUSADO','COMPLETADO','CANCELADO')
                  NOT NULL DEFAULT 'PLANIFICADO',
    promotor_id   BIGINT,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Proyecto de prueba inicial
INSERT INTO proyecto (nombre_clave, denominacion, fecha_inicio, estado)
VALUES ('PROJ-001', 'Sistema de Gestión Interna', '2025-01-01', 'EN_CURSO');

-- Usuario administrador inicial (password: Admin1234!)
INSERT INTO users (id, name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@example.com',
    '$2a$12$placeholderHashReplaceWithRealBCryptHash',
    'ADMIN',
    'ACTIVE'
);

