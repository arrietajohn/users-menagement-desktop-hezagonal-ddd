package com.jcaa.usersmanagement.domain.model.trabajogrado;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public final class TrabajoGrado {
    private final Integer numeroOrden;
    private final String tema;
    private final LocalDate fechaInicio;
    private final String alumnoMatricula;

    public static TrabajoGrado create(
            Integer numeroOrden,
            String tema,
            LocalDate fechaInicio,
            String alumnoMatricula) {
        if (tema == null || tema.isBlank())
            throw new IllegalArgumentException("El tema no puede estar vacío");
        if (fechaInicio == null)
            throw new IllegalArgumentException("La fecha de inicio es obligatoria");
        if (alumnoMatricula == null || alumnoMatricula.isBlank())
            throw new IllegalArgumentException("La matrícula del alumno es obligatoria");
        return TrabajoGrado.builder()
                .numeroOrden(numeroOrden)
                .tema(tema)
                .fechaInicio(fechaInicio)
                .alumnoMatricula(alumnoMatricula)
                .build();
    }
}