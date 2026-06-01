-- =============================================
-- Script de creación de la base de datos
-- Gestión de Usuarios - Arquitectura Hexagonal
-- =============================================

CREATE TABLE IF NOT EXISTS "user" (
    id          SERIAL PRIMARY KEY NOT NULL,
    firstName   VARCHAR(100)  NOT NULL,
    lastName    VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    enterprise_id INT NOT NULL,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(20) NOT NULL DEFAULT 'MEMBER',
    status      VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT NOW());

-- Usuario administrador inicial (password: Admin1234!)
INSERT INTO "user" (first_name, last_name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@ecoresiduos.com',
    '$2a$12$placeholderHashReplaceWithRealBCryptHash',
    'ADMIN',
    'ACTIVE'
);

TRUNCATE TABLE "user" RESTART IDENTITY;

