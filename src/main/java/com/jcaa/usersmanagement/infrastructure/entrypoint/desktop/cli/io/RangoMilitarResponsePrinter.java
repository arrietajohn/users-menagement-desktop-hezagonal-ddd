package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RangoMilitarResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class RangoMilitarResponsePrinter {

    private final ConsoleIO console;

    public void print(final RangoMilitarResponse rango) {
        console.println("\n  ----------------------------------------");
        console.println("  ID        : " + rango.id());
        console.println("  Codigo    : " + rango.codigo());
        console.println("  Nombre    : " + rango.nombre());
        console.println("  Descripcion: " + rango.descripcion());
        console.println("  Linea     : " + rango.lineaMilitar());
        console.println("  T. Ascenso: " + rango.tiempoMinimoAscensoMeses() + " meses");
        console.println("  ----------------------------------------");
    }

    public void printList(final java.util.List<RangoMilitarResponse> rangos) {
        if (rangos.isEmpty()) {
            console.println("  No hay rangos registrados.");
            return;
        }
        rangos.forEach(this::print);
    }
}
