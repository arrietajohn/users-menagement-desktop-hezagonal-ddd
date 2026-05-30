package com.jcaa.usersmanagement.domain.model;

public class NombreCientifico {
private final String valor;

    private NombreCientifico(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre científico no puede estar vacío");
        }
        this.valor = valor.trim();
    }

    public static NombreCientifico of(String valor) {
        return new NombreCientifico(valor);
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NombreCientifico that = (NombreCientifico) o;
        return valor.equals(that.valor);
    }

    @Override
    public int hashCode() {
        return valor.hashCode();
    }

    @Override
    public String toString() {
        return valor;
    }   
}
