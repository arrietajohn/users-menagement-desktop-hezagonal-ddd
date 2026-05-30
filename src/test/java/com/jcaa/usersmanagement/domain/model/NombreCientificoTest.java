package com.jcaa.usersmanagement.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NombreCientificoTest {

    @Test
    void CrearNombreCientifico() {
        NombreCientifico nombre = NombreCientifico.of("Panthera leo");
        
        assertNotNull(nombre);
        assertEquals("Panthera leo", nombre.getValor());
    }

    @Test
    void ExcepcionCuandoNombreEsNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            NombreCientifico.of(null);
        });
    }

    @Test
    void ExcepcionCuandoNombreEstaVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            NombreCientifico.of("   ");
        });
    }

    @Test
    void dosNombresIgualesDeberianSerIguales() {
        NombreCientifico n1 = NombreCientifico.of("Panthera leo");
        NombreCientifico n2 = NombreCientifico.of("Panthera leo");
        
        assertEquals(n1, n2);
        assertEquals(n1.hashCode(), n2.hashCode());
    }
}
