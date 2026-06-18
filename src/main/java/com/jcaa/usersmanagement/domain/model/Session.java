package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.InvestigacionId;
import com.jcaa.usersmanagement.domain.valueobject.SalaID;
import com.jcaa.usersmanagement.domain.valueobject.SessionId;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value

public class Session {

    SessionId id;
    SalaID salaID;
    InvestigacionId investigacionId;
    UserId ponenteId;
    UserId chairmanId;
    LocalDate Fecha;
    LocalTime horaInicio;
    LocalTime horaFin;

    // Esta parte permitira que el administrador/organizador pueda registar /crear una sesion

    public static Session registrar(
            final SessionId id,
            final SalaID salaID,
            final InvestigacionId investigacionId,
            final UserId ponenteId,
            final UserId chairmanId,
            final LocalDate Fecha,
            final LocalTime horaInicio,
            final LocalTime horaFin)
    { return new Session (id, salaID, investigacionId, ponenteId, chairmanId, Fecha, horaInicio, horaFin);

    }

}
