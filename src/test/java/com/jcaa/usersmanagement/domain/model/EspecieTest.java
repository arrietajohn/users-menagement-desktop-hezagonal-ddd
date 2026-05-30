package com.jcaa.usersmanagement.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EspecieTest {

    @Test
    void CrearEspecie() {
        EspecieId id = EspecieId.generar();
        NombreCientifico nombreCientifico = NombreCientifico.of("Panthera leo");

        Especie especie = Especie.crear(id, nombreCientifico, "León Africano");

        assertNotNull(especie);
        assertEquals(id, especie.getId());
        assertEquals(nombreCientifico, especie.getNombreCientifico());
        assertEquals("León Africano", especie.getNombreEspañol());
    }

    @Test
    void ActualizarDescripcion() {
        EspecieId id = EspecieId.generar();
        NombreCientifico nc = NombreCientifico.of("Panthera leo");
        Especie especie = Especie.crear(id, nc, "León");

        especie.actualizarDescripcion("León Africano majestuoso en savana");

        assertEquals("León Africano majestuoso en savana", especie.getDescripcionGeneral());
    }

    @Test
    void AgregarExamen() {
        EspecieId id = EspecieId.generar();
        NombreCientifico nc = NombreCientifico.of("Panthera leo");
        Especie especie = Especie.crear(id, nc, "León");

        especie.agregarExamen("Chequeo general - Todo normal");

        assertEquals(1, especie.getHistorialExamenes().size());
    }

    @Test
    void DosEspeciesConMismoIdDeberianSerIguales() {
        EspecieId id = EspecieId.generar();
        NombreCientifico nc = NombreCientifico.of("Panthera leo");

        Especie e1 = Especie.crear(id, nc, "León");
        Especie e2 = Especie.crear(id, nc, "León Africano");

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }
}
