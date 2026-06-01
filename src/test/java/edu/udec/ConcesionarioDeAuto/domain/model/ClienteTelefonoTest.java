package edu.udec.ConcesionarioDeAuto.domain.model;

import edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente.ClienteTelefono;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ClienteTelefonoTest {

    @Test
    void CrearTelefonoValido() {
        ClienteTelefono telefono = new ClienteTelefono("3001234567");

        assertEquals("3001234567", telefono.value());
    }

    @Test
    void FallarSiTelefonoNoEsNumerico() {
        assertThrows(RuntimeException.class,
                () -> new ClienteTelefono("ABCDEF1234"));
    }

    @Test
    void FallarSiTelefonoNoTiene10Digitos() {
        assertThrows(RuntimeException.class,
                () -> new ClienteTelefono("12345"));
    }
}