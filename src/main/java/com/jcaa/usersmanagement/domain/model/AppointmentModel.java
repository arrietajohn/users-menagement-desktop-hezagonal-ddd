package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.AppointmentStatus;
import com.jcaa.usersmanagement.domain.valueobject.AppointmentDate;
import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;
import com.jcaa.usersmanagement.domain.valueobject.AppointmentReason;
import com.jcaa.usersmanagement.domain.valueobject.PatientId;
import com.jcaa.usersmanagement.domain.valueobject.DoctorId;
import lombok.Value;
@Value
public class AppointmentModel {

    AppointmentId id;
    PatientId patientId;
    DoctorId doctorId;
    AppointmentDate appointmentDate;
    AppointmentReason appointmentReason;
    AppointmentStatus appointmentStatus;

    public static AppointmentModel create(
            final AppointmentId id,
            final PatientId patientId,
            final DoctorId doctorId,
            final AppointmentDate appointmentDate,
            final AppointmentReason appointmentReason) {
        return new AppointmentModel(id, patientId, doctorId, appointmentDate, appointmentReason, AppointmentStatus.SCHEDULED);
    }

    public AppointmentModel completed() {
        return new AppointmentModel(id, patientId, doctorId, appointmentDate, appointmentReason, AppointmentStatus.COMPLETED);
    }
    public AppointmentModel canceled() {
        return new AppointmentModel(id, patientId, doctorId, appointmentDate, appointmentReason, AppointmentStatus.CANCELED);
    }
}
