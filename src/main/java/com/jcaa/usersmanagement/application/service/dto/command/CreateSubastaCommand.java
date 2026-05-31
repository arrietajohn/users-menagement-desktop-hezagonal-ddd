package com.jcaa.usersmanagement.application.service.dto.command;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateSubastaCommand {
    private Integer idArticulo;
    private BigDecimal precioInicial;
    private LocalDateTime fechaLimite;

    public Integer getIdArticulo() { return idArticulo; }
    public void setIdArticulo(Integer idArticulo) { this.idArticulo = idArticulo; }
    public BigDecimal getPrecioInicial() { return precioInicial; }
    public void setPrecioInicial(BigDecimal precioInicial) { this.precioInicial = precioInicial; }
    public LocalDateTime getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDateTime fechaLimite) { this.fechaLimite = fechaLimite; }
}