package com.jcaa.usersmanagement.domain.specification;

import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.domain.model.Habitat;

/**
 * Specification - Patrón de diseño para reglas de validación complejas
 * Verifica si una especie es compatible con un hábitat específico
 */
public class EspecieCompatibleConHabitatSpecification {

    /**
     * Verifica si se cumple la especificación
     */
    public boolean isSatisfiedBy(Especie especie, Habitat habitat) {
        if (especie == null || habitat == null) {
            return false;
        }

        // Reglas de compatibilidad (puedes expandir esta lógica)
        String climaEspecie = obtenerClimaIdeal(especie);
        String climaHabitat = habitat.getClima();

        return climaEspecie.equalsIgnoreCase(climaHabitat) || 
               esCompatiblePorTipo(especie, habitat);
    }

    /**
     * Método auxiliar privado (lógica de negocio)
     */
    private String obtenerClimaIdeal(Especie especie) {
        // Aquí podrías tener una tabla de compatibilidad según nombre científico
        String nombre = especie.getNombreCientifico().getValor().toLowerCase();
        
        if (nombre.contains("leo") || nombre.contains("tigris")) return "Tropical";
        if (nombre.contains("penguin")) return "Polar";
        return "Templado"; // default
    }

    private boolean esCompatiblePorTipo(Especie especie, Habitat habitat) {
        // Lógica adicional de compatibilidad
        return true; // Temporal
    }
}
