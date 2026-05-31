package com.jcaa.usersmanagement.application.service.dto.query;

import java.math.BigDecimal;

public class SubastaQueryDto {
    private Integer idSubasta;
    private BigDecimal precioActual;
    private String estado;

    public Integer getIdSubasta() { return idSubasta; }
    public void setIdSubasta(Integer idSubasta) { this.idSubasta = idSubasta; }
    public BigDecimal getPrecioActual() { return precioActual; }
    public void setPrecioActual(BigDecimal precioActual) { this.precioActual = precioActual; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}