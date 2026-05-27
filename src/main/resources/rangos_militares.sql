-- Script: rangos_militares.sql
-- Módulo: RangoMilitar CRUDL
-- Ejercicio 25: Sistema de Gestión Militar
-- Estudiante: jquinteroh2-ops

CREATE TABLE IF NOT EXISTS rangos_militares (
                                                id                          VARCHAR(36)   NOT NULL,
    codigo                      VARCHAR(10)   NOT NULL,
    nombre                      VARCHAR(100)  NOT NULL,
    descripcion                 VARCHAR(500)  NOT NULL DEFAULT '',
    linea_militar               VARCHAR(20)   NOT NULL,
    tiempo_minimo_ascenso_meses INT           NOT NULL DEFAULT 0,
    created_at                  DATETIME      NOT NULL DEFAULT NOW(),
    updated_at                  DATETIME      NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_rangos_militares        PRIMARY KEY (id),
    CONSTRAINT uq_rangos_militares_codigo UNIQUE (codigo),
    CONSTRAINT chk_linea_militar CHECK (linea_militar IN ('OFICIAL','SUBOFICIAL','RECLUTA')),
    CONSTRAINT chk_tiempo_minimo CHECK (tiempo_minimo_ascenso_meses >= 0)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Datos de prueba
INSERT INTO rangos_militares
(id, codigo, nombre, descripcion, linea_militar, tiempo_minimo_ascenso_meses)
VALUES
    ('rm-001', 'RCL', 'Recluta',    'Nivel de ingreso al servicio',     'RECLUTA',    0),
    ('rm-002', 'SOL', 'Soldado',    'Primer rango activo',              'RECLUTA',   12),
    ('rm-003', 'CAB', 'Cabo',       'Lider de escuadra basica',         'SUBOFICIAL', 24),
    ('rm-004', 'SGT', 'Sargento',   'Suboficial de nivel intermedio',   'SUBOFICIAL', 36),
    ('rm-005', 'TEN', 'Teniente',   'Oficial de nivel inicial',         'OFICIAL',    48),
    ('rm-006', 'CAP', 'Capitan',    'Oficial al mando de compania',     'OFICIAL',    60),
    ('rm-007', 'MYR', 'Mayor',      'Oficial superior de estado mayor', 'OFICIAL',    72),
    ('rm-008', 'CRN', 'Coronel',    'Al mando de regimiento o brigada', 'OFICIAL',    96),
    ('rm-009', 'GRL', 'General',    'Maximo rango de linea terrestre',  'OFICIAL',   120);
