package com.jcaa.usersmanagement.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class ExamenMedico {
private final String id;
    private final LocalDateTime fecha;
    private final String tipoExamen;
    private final String resultados;
    private final String observaciones;

    private ExamenMedico(String id, LocalDateTime fecha, String tipoExamen, String resultados, String observaciones) {
        this.id = Objects.requireNonNull(id);
        this.fecha = Objects.requireNonNull(fecha);
        this.tipoExamen = Objects.requireNonNull(tipoExamen);
        this.resultados = resultados;
        this.observaciones = observaciones;
    }

    public static ExamenMedico crear(String tipoExamen, String resultados, String observaciones) {
        return new ExamenMedico(
            java.util.UUID.randomUUID().toString(),
            LocalDateTime.now(),
            tipoExamen,
            resultados,
            observaciones
        );
    }

    public String getId() { return id; }
    public LocalDateTime getFecha() { return fecha; }
    public String getTipoExamen() { return tipoExamen; }
    public String getResultados() { return resultados; }
    public String getObservaciones() { return observaciones; }   
}
