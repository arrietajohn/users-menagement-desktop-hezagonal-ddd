package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;

import java.util.Map;
import lombok.Getter;

public final class AppointmentCanceledDomainEvent extends DomainEvent{

    private static final String EVENT_NAME = "appointment.canceled";

    private final AppointmentId appointmentId;

    public AppointmentCanceledDomainEvent(final AppointmentId appointmentId) {
        super(EVENT_NAME);
        this.appointmentId = appointmentId;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "id", appointmentId.value()
        );
    }
}
