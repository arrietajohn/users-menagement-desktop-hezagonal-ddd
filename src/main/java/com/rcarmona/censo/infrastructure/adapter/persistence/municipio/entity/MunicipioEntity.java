package com.rcarmona.censo.infrastructure.adapter.persistence.municipio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad de Persistencia para Municipio (Mapea a la tabla de MySQL).
 * 
 * @author Rosary Carmona
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Objeto de persistencia físico. Representa el esquema o tabla exacta dentro de la base de datos relacional para el componente Municipio. Solo existe en la capa de Infraestructura.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioEntity
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class MunicipioEntity {
    private Integer id;
    private String nombre;
    private Integer provinciaId;
}


