package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.exception.InvalidSucursalPostalCodeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("SucursalPostalCode")
class SucursalPostalCodeTest {

    // ── flujo feliz

    @Test
    @DisplayName("acepta un código postal de 6 dígitos válido")
    void shouldAcceptValidSixDigitCode() {
        final SucursalPostalCode cp = new SucursalPostalCode("080001");
        assertEquals("080001", cp.value());
    }

    @Test
    @DisplayName("recorta espacios en blanco antes de validar")
    void shouldTrimWhitespace() {
        final SucursalPostalCode cp = new SucursalPostalCode("  110111  ");
        assertEquals("110111", cp.value());
    }

    // ── nulo

    @Test
    @DisplayName("lanza NullPointerException cuando el valor es nulo")
    void shouldThrowWhenNull() {
        assertThrows(NullPointerException.class, () -> new SucursalPostalCode(null));
    }

    // ── vacío

    @Test
    @DisplayName("lanza InvalidSucursalPostalCodeException cuando el valor está vacío")
    void shouldThrowWhenEmpty() {
        assertThrows(InvalidSucursalPostalCodeException.class,
                () -> new SucursalPostalCode(""));
    }

    @Test
    @DisplayName("lanza InvalidSucursalPostalCodeException cuando el valor es solo espacios")
    void shouldThrowWhenBlank() {
        assertThrows(InvalidSucursalPostalCodeException.class,
                () -> new SucursalPostalCode("   "));
    }

    // ── formato inválido

    @ParameterizedTest(name = "rechaza ''{0}''")
    @DisplayName("lanza InvalidSucursalPostalCodeException para formatos inválidos")
    @ValueSource(strings = {
            "1234",       // menos de 6 dígitos
            "1234567",    // más de 6 dígitos
            "08000A",     // contiene letra
            "08-001",     // contiene guion
            "08 001",     // contiene espacio interno
            "abcdef"      // solo letras
    })
    void shouldThrowWhenFormatIsInvalid(final String invalid) {
        assertThrows(InvalidSucursalPostalCodeException.class,
                () -> new SucursalPostalCode(invalid));
    }
}
