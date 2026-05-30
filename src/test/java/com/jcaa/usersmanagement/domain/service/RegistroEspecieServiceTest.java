package com.jcaa.usersmanagement.domain.service;

import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.domain.model.NombreCientifico;
import com.jcaa.usersmanagement.domain.service.Impl.RegistroEspecieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegistroEspecieServiceTest {

    private RegistroEspecieService registroEspecieService;

    @BeforeEach
    void setUp() {
        this.registroEspecieService = new RegistroEspecieServiceImpl();
    }

    @Test
    void deberiaRegistrarEspecieCorrectamente() {
        NombreCientifico nombreCientifico = NombreCientifico.of("Panthera leo");
        String nombreEspañol = "León Africano";
        String descripcion = "Gran felino social de la sabana africana";

        Especie especie = registroEspecieService.registrarEspecie(
            nombreCientifico, 
            nombreEspañol, 
            descripcion
        );

        assertNotNull(especie);
        assertNotNull(especie.getId());
        assertEquals(nombreCientifico, especie.getNombreCientifico());
        assertEquals(nombreEspañol, especie.getNombreEspañol());
        assertEquals(descripcion, especie.getDescripcionGeneral());
    }

    @Test
    void deberiaLanzarExcepcionConNombreCientificoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            registroEspecieService.registrarEspecie(null, "León", "Descripción");
        });
    }
}
