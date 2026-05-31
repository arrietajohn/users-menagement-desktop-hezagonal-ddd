package com.jcaa.usersmanagement.application.service.dto.query;

import java.math.BigDecimal;

public class OfertaQueryDto {
    private Integer idOferta;
    private BigDecimal monto;

    public Integer getIdOferta() { return idOferta; }
    public void setIdOferta(Integer idOferta) { this.idOferta = idOferta; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
}