package com.rcarmona.censo.domain.municipio.exception;

/**
 * ExcepciÃ³n personalizada de nuestro Dominio.
 * 
 * En lugar de usar errores genÃ©ricos de Java (como los molestos NullPointerException) o 
 * errores que provienen directamente de MySQL, creamos esta excepciÃ³n para hablar en el 
 * "idioma de nuestro negocio" (Lenguaje Ubicuo). AsÃ­, cuando intentamos buscar un municipio 
 * y no existe, el programa lanza este error claro y especÃ­fico, haciendo que depurar o 
 * mostrar mensajes al usuario final sea sÃºper fÃ¡cil.
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
 * - Excepción personalizada del sistema. Maneja flujos de error controlados para situaciones específicas de MunicipioNotFound, devolviendo mensajes de error claros y semánticos al usuario final en lugar de fallos genéricos.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioNotFoundException
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public final class MunicipioNotFoundException extends DomainException {

    /**
     * Construye una nueva excepciÃ³n con un mensaje detallado.
     *
     * @param id El identificador del municipio que no fue encontrado.
     */
    public MunicipioNotFoundException(Integer id) {
        super(String.format("No se encontrÃ³ el municipio con ID: %d", id));
    }
}


