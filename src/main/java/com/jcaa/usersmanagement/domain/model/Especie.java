package com.jcaa.usersmanagement.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Entidad Especie - Aggregate Root
 */
public class Especie {

    private final EspecieId id;
    private final NombreCientifico nombreCientifico;
    private String nombreEspañol;
    private String descripcionGeneral;
    private final List<String> historialExamenes;
    private Habitat habitatActual;

    private Especie(EspecieId id, NombreCientifico nombreCientifico, String nombreEspañol) {
        this.id = Objects.requireNonNull(id);
        this.nombreCientifico = Objects.requireNonNull(nombreCientifico);
        this.nombreEspañol = Objects.requireNonNull(nombreEspañol);
        this.historialExamenes = new ArrayList<>();
    }

    public static Especie crear(EspecieId id, NombreCientifico nombreCientifico, String nombreEspañol) {
        return new Especie(id, nombreCientifico, nombreEspañol);
    }

    public void actualizarDescripcion(String descripcion) {
        this.descripcionGeneral = descripcion;
    }

    public void agregarExamen(String examen) {
        if (examen != null && !examen.trim().isEmpty()) {
            this.historialExamenes.add(examen);
        }
    }

    public EspecieId getId() { return id; }
    public NombreCientifico getNombreCientifico() { return nombreCientifico; }
    public String getNombreEspañol() { return nombreEspañol; }
    public String getDescripcionGeneral() { return descripcionGeneral; }
    
    public List<String> getHistorialExamenes() { 
        return Collections.unmodifiableList(historialExamenes); 
    }

    public Habitat getHabitatActual() { return habitatActual; }

    public void asignarHabitat(Habitat habitat) {
        if (habitat == null) {
            throw new IllegalArgumentException("El hábitat no puede ser nulo");
        }
        
        if (!esCompatibleCon(habitat)) {
            throw new IllegalArgumentException("Hábitat no compatible con la especie " + nombreCientifico.getValor());
        }

        this.habitatActual = habitat;
    }

    private boolean esCompatibleCon(Habitat habitat) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Especie especie = (Especie) o;
        return id.equals(especie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
