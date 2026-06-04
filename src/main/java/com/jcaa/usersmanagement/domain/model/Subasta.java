package com.jcaa.usersmanagement.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Subasta {
    private Integer idSubasta;
    private Integer idArticulo;
    private BigDecimal precioInicial;
    private BigDecimal precioActual;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaLimite;
    private String estado;

    public boolean estaVigente() {
        return "ACTIVA".equalsIgnoreCase(this.estado) &&
                (this.fechaLimite == null || LocalDateTime.now().isBefore(this.fechaLimite));
    }

    public Integer getIdSubasta() { return idSubasta; }
    public void setIdSubasta(Integer idSubasta) { this.idSubasta = idSubasta; }
    public Integer getIdArticulo() { return idArticulo; }
    public void setIdArticulo(Integer idArticulo) { this.idArticulo = idArticulo; }
    public BigDecimal getPrecioInicial() { return precioInicial; }
    public void setPrecioInicial(BigDecimal precioInicial) { this.precioInicial = precioInicial; }
    public BigDecimal getPrecioActual() { return precioActual; }
    public void setPrecioActual(BigDecimal precioActual) { this.precioActual = precioActual; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDateTime getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDateTime fechaLimite) { this.fechaLimite = fechaLimite; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}