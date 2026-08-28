package com.jcaa.usersmanagement.application.port.in.ofertaempleo;

import com.jcaa.usersmanagement.application.service.dto.command.CreateOfertaEmpleoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateOfertaEmpleoCommand;
import com.jcaa.usersmanagement.domain.model.OfertaEmpleoModel;

import java.util.List;

public interface OfertaEmpleoUseCase {

    void create(CreateOfertaEmpleoCommand command);

    OfertaEmpleoModel getById(String id);

    List<OfertaEmpleoModel> getAll();

    void update(UpdateOfertaEmpleoCommand command);

    void delete(String id);
}