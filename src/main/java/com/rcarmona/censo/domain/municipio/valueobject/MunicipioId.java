package com.rcarmona.censo.domain.municipio.valueobject;

/**
 * Objeto de Valor (Value Object) que representa el identificador de un municipio.
 * 
 * En lugar de usar un simple nÃºmero entero (`Integer`) suelto por todo el cÃ³digo, lo envolvemos 
 * en esta clase para darle un significado real. AsÃ­, cuando leamos el cÃ³digo, sabremos exactamente 
 * que estamos pasando el "ID del Municipio" y no cualquier otro nÃºmero al azar. AdemÃ¡s, nos 
 * garantiza que el ID nunca podrÃ¡ ser nulo desde el momento de su creaciÃ³n.
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
 * - Value Object (Objeto de Valor) inmutable. Define una característica o identificador conceptual atómico del negocio con sus propias reglas de auto-validación.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioId
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public record MunicipioId(Integer value) {
    public MunicipioId {
        if (value == null) throw new IllegalArgumentException("El ID del municipio no puede ser nulo");
        if (value < 0) throw new IllegalArgumentException("El ID del municipio no puede ser negativo");
    }
}


