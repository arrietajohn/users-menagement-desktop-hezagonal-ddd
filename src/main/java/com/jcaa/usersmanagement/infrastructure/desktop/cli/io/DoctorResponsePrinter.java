package com.jcaa.usersmanagement.infrastructure.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.desktop.dto.doctor.DoctorResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DoctorResponsePrinter {

    private final ConsoleIO console;

    public void print(final DoctorResponse doc) {
        console.println("\n    [ Datos del Médico Encontrado ]");
        console.println("    ----------------------------------------");
        console.println("    ID: " + doc.id());
        console.println("    Nombre: " + doc.name());
        console.println("    NIF: " + doc.nif());
        console.println("    Licencia Médica: " + doc.medicalLicense());
        console.println("    Categoría: " + doc.category());
        console.println("    Estado: " + doc.status());
        console.println("    Teléfono: " + doc.phone());
        console.println("    Dirección: " + doc.address() + ", " + doc.population() + " (" + doc.province() + ")");
        console.println("    ----------------------------------------");
    }

    public void printList(final List<DoctorResponse> doctors) {
        if (doctors.isEmpty()) {
            console.println("    No se encontraron médicos registrados.");
            return;
        }
        console.println("\n    =================================================================================");
        console.println("    ID                                   | Nombre               | NIF        | Estado");
        console.println("    =================================================================================");
        for (final DoctorResponse doc : doctors) {
            console.printf("    %-36s | %-20s | %-10s | %s%n", doc.id(), doc.name(), doc.nif(), doc.status());
        }
        console.println("    =================================================================================");
    }
}