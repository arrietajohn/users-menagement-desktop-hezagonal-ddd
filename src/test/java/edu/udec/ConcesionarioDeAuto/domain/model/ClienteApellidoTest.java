package edu.udec.ConcesionarioDeAuto.domain.model.Cliente;
import edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente.ClienteApellido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteApellidoTest {

    @Test
    void CreaApellidoValido() {
        ClienteApellido apellido = new ClienteApellido("Perez");

        assertEquals("Perez", apellido.value());
    }

    @Test
    void FallaSiApellidoEsNull() {
        assertThrows(RuntimeException.class,
                () -> new ClienteApellido(null));
    }
}