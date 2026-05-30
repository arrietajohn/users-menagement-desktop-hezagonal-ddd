package com.jcaa.usersmanagement.domain.model;

import java.util.Objects;

public class Habitat {
private final String nombre;
    private final String clima;
    private final String tipoVegetacion;

    private Habitat(String nombre, String clima, String tipoVegetacion) {
        this.nombre = Objects.requireNonNull(nombre);
        this.clima = Objects.requireNonNull(clima);
        this.tipoVegetacion = Objects.requireNonNull(tipoVegetacion);
    }

    public static Habitat of(String nombre, String clima, String tipoVegetacion) {
        return new Habitat(nombre, clima, tipoVegetacion);
    }

    public String getNombre() { return nombre; }
    public String getClima() { return clima; }
    public String getTipoVegetacion() { return tipoVegetacion; }   
}
