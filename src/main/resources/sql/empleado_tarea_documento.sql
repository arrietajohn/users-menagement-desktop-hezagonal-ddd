-- Tabla empleado
CREATE TABLE IF NOT EXISTS empleado (
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre             VARCHAR(100)  NOT NULL,
  apellido           VARCHAR(100)  NOT NULL,
  email              VARCHAR(150)  NOT NULL UNIQUE,
  cargo              VARCHAR(100)  NOT NULL,
  fecha_contratacion DATE          NOT NULL,
  estado             VARCHAR(20)   NOT NULL DEFAULT 'ACTIVO',
  created_at         DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at         DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT chk_empleado_estado CHECK (estado IN ('ACTIVO','INACTIVO'))
);

-- Tabla tarea (FK a empleado)
CREATE TABLE IF NOT EXISTS tarea (
  id               BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo           VARCHAR(200)  NOT NULL,
  descripcion      VARCHAR(1000),
  prioridad        VARCHAR(10)   NOT NULL,
  estado           VARCHAR(20)   NOT NULL DEFAULT 'PENDIENTE',
  fecha_vencimiento DATE,
  empleado_id      BIGINT        NOT NULL,
  created_at       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_tarea_empleado  FOREIGN KEY (empleado_id) REFERENCES empleado(id),
  CONSTRAINT chk_tarea_prioridad CHECK (prioridad IN ('ALTA','MEDIA','BAJA')),
  CONSTRAINT chk_tarea_estado   CHECK (estado IN ('PENDIENTE','EN_PROGRESO','COMPLETADA','CANCELADA'))
);

-- Tabla documento (FK a empleado como autor)
CREATE TABLE IF NOT EXISTS documento (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo        VARCHAR(200)  NOT NULL,
  tipo          VARCHAR(20)   NOT NULL,
  contenido     VARCHAR(5000),
  fecha_creacion DATE          NOT NULL,
  estado        VARCHAR(20)   NOT NULL DEFAULT 'BORRADOR',
  autor_id      BIGINT        NOT NULL,
  created_at    DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_documento_autor FOREIGN KEY (autor_id) REFERENCES empleado(id),
  CONSTRAINT chk_documento_tipo   CHECK (tipo IN ('CONTRATO','INFORME','FACTURA','OTRO')),
  CONSTRAINT chk_documento_estado CHECK (estado IN ('BORRADOR','PUBLICADO','ARCHIVADO'))
);

-- Datos de prueba: empleados
INSERT INTO empleado (nombre, apellido, email, cargo, fecha_contratacion, estado) VALUES
  ('Ana',    'García',    'ana.garcia@empresa.com',    'Desarrolladora',      '2022-03-15', 'ACTIVO'),
  ('Luis',   'Martínez',  'luis.martinez@empresa.com', 'Analista',            '2021-07-01', 'ACTIVO'),
  ('Sofía',  'López',     'sofia.lopez@empresa.com',   'Diseñadora UX',       '2023-01-10', 'ACTIVO'),
  ('Carlos', 'Ramírez',   'carlos.ramirez@empresa.com','Gerente de proyecto',  '2020-11-20', 'ACTIVO'),
  ('Laura',  'Torres',    'laura.torres@empresa.com',  'QA Engineer',         '2022-09-05', 'INACTIVO');

-- Datos de prueba: tareas
INSERT INTO tarea (titulo, descripcion, prioridad, estado, fecha_vencimiento, empleado_id) VALUES
  ('Implementar módulo de pagos',  'Integrar pasarela de pago Stripe',  'ALTA',  'EN_PROGRESO', '2025-07-31', 1),
  ('Revisar diseño de pantallas',  'Ajustar paleta de colores y tipografía', 'MEDIA', 'PENDIENTE',   '2025-08-15', 3),
  ('Pruebas de regresión v2.0',    'Ejecutar suite completa de pruebas', 'ALTA',  'PENDIENTE',   '2025-07-20', 5),
  ('Documentar API REST',          'Swagger para endpoints de usuarios', 'BAJA',  'COMPLETADA',  NULL,         2),
  ('Reunión con cliente',          'Presentar avance del sprint 4',      'MEDIA', 'PENDIENTE',   '2025-07-10', 4);

-- Datos de prueba: documentos
INSERT INTO documento (titulo, tipo, contenido, fecha_creacion, estado, autor_id) VALUES
  ('Contrato empleado Ana García',      'CONTRATO', 'Contrato de trabajo indefinido.',             '2022-03-15', 'PUBLICADO',  4),
  ('Informe sprint 4',                  'INFORME',  'Resumen de actividades del sprint 4.',        '2025-06-30', 'BORRADOR',   2),
  ('Factura proveedor TechSoft',        'FACTURA',  'Factura #2025-0042 por servicios de nube.',   '2025-06-01', 'PUBLICADO',  4),
  ('Informe de pruebas v2.0',           'INFORME',  'Resultados de la suite de pruebas.',          '2025-07-05', 'BORRADOR',   5),
  ('Especificación módulo de pagos',    'OTRO',     'Documento de requerimientos funcionales.',    '2025-05-20', 'ARCHIVADO',  1);
