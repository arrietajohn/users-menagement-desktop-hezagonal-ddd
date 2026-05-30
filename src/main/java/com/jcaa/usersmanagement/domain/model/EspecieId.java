package com.jcaa.usersmanagement.domain.model;

import java.util.Objects;
import java.util.UUID;

public class EspecieId {

    private final String valor;

    private EspecieId(String valor) {
        this.valor = Objects.requireNonNull(valor);
    }

    public static EspecieId generar() {
        return new EspecieId(UUID.randomUUID().toString());
    }

    public static EspecieId of(String valor) {
        return new EspecieId(valor);
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspecieId that = (EspecieId) o;
        return valor.equals(that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
