package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateSessionCommand;
import com.jcaa.usersmanagement.domain.model.Session;
import com.jcaa.usersmanagement.domain.valueobject.InvestigacionId;
import com.jcaa.usersmanagement.domain.valueobject.SalaID;
import com.jcaa.usersmanagement.domain.valueobject.SessionId;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalTime;

@UtilityClass
public class SessionApplicationMapper {

    public Session fromCreateCommandToModel(final CreateSessionCommand command) {
        return Session.registrar(
                new SessionId(command.id()),
                new SalaID(command.salaId()),
                new InvestigacionId(command.investigacionId()),
                new UserId(command.ponenteId()),
                new UserId(command.chairmanId()),
                LocalDate.from(command.fecha()),
                LocalTime.from(command.horaInicio()),
                LocalTime.from(command.horaFin())
        );
    }
}