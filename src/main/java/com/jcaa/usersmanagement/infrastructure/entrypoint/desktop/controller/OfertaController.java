package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.service.OfertaService;
import com.jcaa.usersmanagement.application.service.dto.command.CreateOfertaCommand;

public class OfertaController {
    private final OfertaService service;

    public OfertaController(OfertaService service) { this.service = service; }
    public void pujar(CreateOfertaCommand cmd) { service.registrar(cmd); }
}