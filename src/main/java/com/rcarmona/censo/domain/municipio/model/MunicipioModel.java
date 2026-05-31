package com.rcarmona.censo.domain.municipio.model;

import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;

/**
 * Entidad Principal (Aggregate Root) de nuestro dominio.
 * 
 * Piensa en esta clase como el corazÃ³n de nuestra regla de negocio para los Municipios. 
 * AquÃ­ nos aseguramos de que los datos siempre sean correctos y coherentes al momento de nacer. 
 * Por ejemplo, el constructor se encarga de que jamÃ¡s podamos crear un municipio sin nombre.
 * Lo mejor de esta capa es que es "pura": no sabe absolutamente nada de bases de datos, 
 * JSON o interfaces grÃ¡ficas; solo sabe de las reglas estrictas de nuestro censo.
 * 
 * @author Rosary Carmona
 */
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Entidad principal (Aggregate Root) del modelo de negocio. Contiene atributos, métodos y todas las reglas estrictas de validación de negocio para un Municipio. Nunca se acopla a librerías externas o bases de datos.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioModel
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class MunicipioModel {
    private final MunicipioId id;
    private final String nombre;
    private final Integer provinciaId;

    public MunicipioModel(MunicipioId id, String nombre, Integer provinciaId) {
        if (nombre == null || nombre.trim().isBlank()) {
            throw new IllegalArgumentException("El nombre del municipio es obligatorio");
        }
        this.id = id;
        this.nombre = nombre.trim();
        this.provinciaId = provinciaId;
    }

    public static MunicipioModel createNew(String nombre, Integer provinciaId) {
        return new MunicipioModel(null, nombre, provinciaId);
    }

    public MunicipioId getId() { return id; }
    public String getNombre() { return nombre; }
    public Integer getProvinciaId() { return provinciaId; }
}


