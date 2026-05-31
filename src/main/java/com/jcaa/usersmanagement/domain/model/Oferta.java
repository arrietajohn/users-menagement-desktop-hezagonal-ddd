package com.jcaa.usersmanagement.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Oferta {
    private Integer idOferta;
    private Integer idSubasta;
    private Integer idUsuarioOfertante;
    private BigDecimal monto;
    private LocalDateTime fechaHora;

    public Integer getIdOferta() { return idOferta; }
    public void setIdOferta(Integer idOferta) { this.idOferta = idOferta; }
    public Integer getIdSubasta() { return idSubasta; }
    public void setIdSubasta(Integer idSubasta) { this.idSubasta = idSubasta; }
    public Integer getIdUsuarioOfertante() { return idUsuarioOfertante; }
    public void setIdUsuarioOfertante(Integer idUsuarioOfertante) { this.idUsuarioOfertante = idUsuarioOfertante; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}