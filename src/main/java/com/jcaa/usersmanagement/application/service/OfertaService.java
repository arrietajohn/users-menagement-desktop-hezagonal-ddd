package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.RegistrarOfertaUseCase;
import com.jcaa.usersmanagement.application.port.in.ListarOfertasPorSubastaUseCase;
import com.jcaa.usersmanagement.application.port.in.BuscarOfertaPorIdUseCase;
import com.jcaa.usersmanagement.application.port.out.OfertaRepositoryPort;
import com.jcaa.usersmanagement.application.port.out.SubastaRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateOfertaCommand;
import com.jcaa.usersmanagement.domain.model.Oferta;
import com.jcaa.usersmanagement.domain.model.Subasta;
import com.jcaa.usersmanagement.domain.exception.SubastaNoEncontradaException;
import com.jcaa.usersmanagement.domain.exception.OfertaInvalidaException;

import java.util.List;
import java.util.Optional;

public class OfertaService implements RegistrarOfertaUseCase, ListarOfertasPorSubastaUseCase, BuscarOfertaPorIdUseCase {
    private final OfertaRepositoryPort ofertaRepositoryPort;
    private final SubastaRepositoryPort subastaRepositoryPort;

    public OfertaService(OfertaRepositoryPort ofertaRepositoryPort, SubastaRepositoryPort subastaRepositoryPort) {
        this.ofertaRepositoryPort = ofertaRepositoryPort;
        this.subastaRepositoryPort = subastaRepositoryPort;
    }

    @Override
    public void registrar(CreateOfertaCommand command) {
        Subasta subasta = subastaRepositoryPort.buscarPorId(command.getIdSubasta())
                .orElseThrow(() -> new SubastaNoEncontradaException("La subasta referenciada no existe."));

        if (!subasta.estaVigente()) {
            throw new OfertaInvalidaException("La subasta no está vigente o ya finalizó.");
        }
        if (command.getMonto().compareTo(subasta.getPrecioActual()) <= 0) {
            throw new OfertaInvalidaException("El monto debe superar al costo actual.");
        }

        Oferta oferta = new Oferta();
        oferta.setIdSubasta(command.getIdSubasta());
        oferta.setIdUsuarioOfertante(command.getIdUsuarioOfertante());
        oferta.setMonto(command.getMonto());
        oferta.setFechaHora(java.time.LocalDateTime.now());

        ofertaRepositoryPort.guardar(oferta);
        subasta.setPrecioActual(command.getMonto());
        subastaRepositoryPort.actualizar(subasta);
    }

    @Override
    public List<Oferta> listarPorSubasta(Integer idSubasta) {
        return ofertaRepositoryPort.buscarPorSubasta(idSubasta);
    }

    @Override
    public Optional<Oferta> buscarPorId(Integer id) {
        return ofertaRepositoryPort.buscarPorId(id);
    }
}