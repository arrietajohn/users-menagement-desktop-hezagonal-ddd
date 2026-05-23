package com.rcarmona.censo.domain.municipio.event;

import java.time.LocalDateTime;
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Evento de Dominio puro. Representa un suceso histórico inmutable (algo que ya pasó) relacionado con Domain. Facilita el desarrollo de una arquitectura orientada a eventos con muy bajo acoplamiento.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: DomainEvent
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public abstract class DomainEvent {
    private final LocalDateTime occurredOn;

    protected DomainEvent() {
        this.occurredOn = LocalDateTime.now();
    }

    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}


