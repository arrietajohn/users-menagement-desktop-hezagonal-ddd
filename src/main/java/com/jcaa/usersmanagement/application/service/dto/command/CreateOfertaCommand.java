package com.jcaa.usersmanagement.application.service.dto.command;

import java.math.BigDecimal;

public class CreateOfertaCommand {
    private Integer idSubasta;
    private Integer idUsuarioOfertante;
    private BigDecimal monto;

    public Integer getIdSubasta() { return idSubasta; }
    public void setIdSubasta(Integer idSubasta) { this.idSubasta = idSubasta; }
    public Integer getIdUsuarioOfertante() { return idUsuarioOfertante; }
    public void setIdUsuarioOfertante(Integer idUsuarioOfertante) { this.idUsuarioOfertante = idUsuarioOfertante; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
}