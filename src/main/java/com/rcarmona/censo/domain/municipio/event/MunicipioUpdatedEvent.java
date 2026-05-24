package com.rcarmona.censo.domain.municipio.event;

import lombok.Getter;

@Getter
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Evento de Dominio puro. Representa un suceso histórico inmutable (algo que ya pasó) relacionado con MunicipioUpdated. Facilita el desarrollo de una arquitectura orientada a eventos con muy bajo acoplamiento.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: MunicipioUpdatedEvent
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public class MunicipioUpdatedEvent extends DomainEvent {
    private final String municipioId;

    public MunicipioUpdatedEvent(final String municipioId) {
        super();
        this.municipioId = municipioId;
    }
}


