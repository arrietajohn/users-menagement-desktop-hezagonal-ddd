package com.rcarmona.censo.infrastructure.adapter.persistence.municipio.mapper;

import com.rcarmona.censo.domain.municipio.model.MunicipioModel;
import com.rcarmona.censo.domain.municipio.valueobject.MunicipioId;
import com.rcarmona.censo.infrastructure.adapter.persistence.municipio.entity.MunicipioEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Suite de pruebas unitarias (Testing). Valida automáticamente el comportamiento y las reglas de negocio de la clase MunicipioPersistenceMapper, garantizando que no existan regresiones en el código mediante escenarios de éxito y fallo.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioPersistenceMapperTest
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
class MunicipioPersistenceMapperTest {

    @Test
    void shouldMapToDomain() {
        MunicipioEntity entity = new MunicipioEntity(1, "Cartagena", 13);
        MunicipioModel model = MunicipioPersistenceMapper.toDomain(entity);
        
        assertNotNull(model);
        assertEquals("Cartagena", model.getNombre());
        assertEquals(13, model.getProvinciaId());
    }

    @Test
    void shouldMapToEntity() {
        MunicipioModel model = new MunicipioModel(new MunicipioId(1), "Cartagena", 13);
        MunicipioEntity entity = MunicipioPersistenceMapper.toEntity(model);
        
        assertNotNull(entity);
        assertEquals("Cartagena", entity.getNombre());
        assertEquals(13, entity.getProvinciaId());
    }

    @Test
    void shouldExtractFromResultSet() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(rs.getInt("id_municipio")).thenReturn(1);
        Mockito.when(rs.getString("nombre_municipio")).thenReturn("Cartagena");
        Mockito.when(rs.getInt("id_provincia")).thenReturn(13);

        MunicipioEntity entity = MunicipioPersistenceMapper.extractFromResultSet(rs);
        assertNotNull(entity);
        assertEquals("Cartagena", entity.getNombre());
        assertEquals(13, entity.getProvinciaId());
    }
}
