package com.jcaa.usersmanagement.domain.model;

/**
 * Factory para la creación de Especies
 * Centraliza la lógica de creación y validaciones iniciales
 */
public class EspecieFactory {

    /**
     * Crea una nueva Especie aplicando reglas de dominio
     */
    public static Especie crear(
            String nombreCientificoStr,
            String nombreEspañol,
            String descripcion) {

        // Validaciones iniciales
        if (nombreCientificoStr == null || nombreCientificoStr.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre científico es obligatorio");
        }

        if (nombreEspañol == null || nombreEspañol.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre en español es obligatorio");
        }

        // Crear Value Objects
        NombreCientifico nombreCientifico = NombreCientifico.of(nombreCientificoStr);
        EspecieId id = EspecieId.generar();

        // Crear la Entidad
        Especie especie = Especie.crear(id, nombreCientifico, nombreEspañol);

        // Aplicar descripción si existe
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            especie.actualizarDescripcion(descripcion);
        }

        return especie;
    }
}
