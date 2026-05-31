package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.service.SubastaService;
import com.jcaa.usersmanagement.application.service.dto.command.CreateSubastaCommand;
import com.jcaa.usersmanagement.domain.model.Subasta;
import java.util.List;

public class SubastaController {
    private final SubastaService service;

    public SubastaController(SubastaService service) { this.service = service; }
    public void crear(CreateSubastaCommand cmd) { service.crear(cmd); }
    public List<Subasta> listar() { return service.listarTodas(); }
    public void borrar(Integer id) { service.eliminar(id); }
}