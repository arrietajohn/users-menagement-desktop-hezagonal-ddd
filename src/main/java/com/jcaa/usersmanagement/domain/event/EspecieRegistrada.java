package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.EspecieId;
import java.time.LocalDateTime;

/**
 * Evento de Dominio - Se publica cuando se registra una nueva especie
 */
public class EspecieRegistrada {

    private final EspecieId especieId;
    private final String nombreCientifico;
    private final String nombreEspañol;
    private final LocalDateTime fechaOcurrencia;
    private final String registradoPor;

    public EspecieRegistrada(EspecieId especieId, String nombreCientifico, 
                           String nombreEspañol, String registradoPor) {
        this.especieId = especieId;
        this.nombreCientifico = nombreCientifico;
        this.nombreEspañol = nombreEspañol;
        this.fechaOcurrencia = LocalDateTime.now();
        this.registradoPor = registradoPor;
    }

    // Getters
    public EspecieId getEspecieId() { return especieId; }
    public String getNombreCientifico() { return nombreCientifico; }
    public String getNombreEspañol() { return nombreEspañol; }
    public LocalDateTime getFechaOcurrencia() { return fechaOcurrencia; }
    public String getRegistradoPor() { return registradoPor; }
}
