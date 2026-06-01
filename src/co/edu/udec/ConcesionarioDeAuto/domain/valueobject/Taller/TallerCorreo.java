package edu.udec.ConcesionarioDeAuto.domain.valueobject.Taller;

import edu.udec.ConcesionarioDeAuto.domain.exceptions.CorreoInvalidoExcepcion;
import java.util.Objects;
import java.util.regex.Pattern;

public record TallerCorreo(String value) {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(
                    "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"
            );

    public TallerCorreo {

        final String valorNormalizado =
                Objects.requireNonNull(value).trim().toLowerCase();

        validarVacio(valorNormalizado);
        validarFormato(valorNormalizado);

        value = valorNormalizado;
    }

    private static void validarVacio(final String valor) {

        if (valor.isEmpty()) {
            throw CorreoInvalidoExcepcion.correoNulo();
        }
    }

    private static void validarFormato(final String valor) {

        if (!EMAIL_PATTERN.matcher(valor).matches()) {
            throw CorreoInvalidoExcepcion.correoInvalido();
        }
    }
}