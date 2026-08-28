package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateOfertaEmpleoCommand;
import com.jcaa.usersmanagement.domain.model.OfertaEmpleoModel;
import com.jcaa.usersmanagement.domain.valueobject.ofertaempleo.*;

public class OfertaEmpleoApplicationMapper {

    public static OfertaEmpleoModel toModel(CreateOfertaEmpleoCommand cmd) {
        return new OfertaEmpleoModel(
                new OfertaEmpleoId(cmd.id()),
                new Titulo(cmd.titulo()),
                new Descripcion(cmd.descripcion()),
                new Empresa(cmd.empresa()),
                new Ubicacion(cmd.ubicacion()),
                new Salario(cmd.salario()),
                new EstadoOferta(cmd.estado())
        );
    }
}