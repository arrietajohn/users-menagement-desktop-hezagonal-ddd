-- =============================================
-- Script de extensión de la base de datos
-- Gestión de Médicos - Módulo Hospitalario (DDD)
-- =============================================

USE crud_usuarios;

CREATE TABLE IF NOT EXISTS doctors (
                                       id                      VARCHAR(36)  NOT NULL PRIMARY KEY,
    name                    VARCHAR(150) NOT NULL,
    address                 VARCHAR(255) NOT NULL,
    phone                   VARCHAR(20)  NOT NULL,
    population              VARCHAR(100) NOT NULL,
    province                VARCHAR(100) NOT NULL,
    postal_code             VARCHAR(20)  NOT NULL,
    nif                     VARCHAR(20)  NOT NULL UNIQUE,
    social_security_number  VARCHAR(30)  NOT NULL UNIQUE,
    medical_license         VARCHAR(30)  NOT NULL UNIQUE,
    category                ENUM('TITULAR', 'INTERINO') NOT NULL,
    status                  ENUM('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED') NOT NULL DEFAULT 'ACTIVE',
    created_at              DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Médico inicial de prueba (Cartagena, Bolívar)
INSERT INTO doctors (
    id,
    name,
    address,
    phone,
    population,
    province,
    postal_code,
    nif,
    social_security_number,
    medical_license,
    category,
    status
)
VALUES (
           '99999999-9999-9999-9999-999999999991',
           'Dr. Juan Carlos Pérez',
           'Cra 4 #12-34, Bocagrande',
           '3001234567',
           'Cartagena',
           'Bolívar',
           '130001',
           '12345678A',
           'SS-987654321',
           'MED-554433',
           'TITULAR',
           'ACTIVE'
       );