package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidLineaMilitarException;

public enum LineaMilitar {
    OFICIAL,
    SUBOFICIAL,
    RECLUTA,

    public static LineaMilitar fromString(final String value) {
        for  (final LineaMilitar linea : values()) {
            if (linea.name().equalsIgnoreCase(value)) {
                return linea;
            }
        }
        throw InvalidLineaMilitarException.becauseValueIsInvalid(value);
    }
}
