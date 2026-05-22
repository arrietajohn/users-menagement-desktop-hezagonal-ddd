package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.AppointmentModel;

import java.util.Map;
import lombok.Getter;
@Getter
public class AppointmentUpdatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "appointment.updated";

    private final AppointmentModel appointment;

    public AppointmentUpdatedDomainEvent(final AppointmentModel appointment) {
        super(EVENT_NAME);
        this.appointment = appointment;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "id", appointment.getId().value(),
                "doctorId", appointment.getDoctorId().value(),
                "patientId", appointment.getPatientId().value(),
                "date", String.valueOf(appointment.getAppointmentDate().value()),
                "reason", appointment.getAppointmentReason().value(),
                "status", appointment.getAppointmentStatus().name()
        );
    }
}
