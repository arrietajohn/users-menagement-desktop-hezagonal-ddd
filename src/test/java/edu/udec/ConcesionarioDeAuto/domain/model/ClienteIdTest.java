package edu.udec.ConcesionarioDeAuto.domain.model.Cliente;

import edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente.ClienteId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteIdTest {

    @Test
    void CreaIdValido() {
        ClienteId id = new ClienteId("123");

        assertEquals("123", id.value());
    }

    @Test
    void FallaSiIdEsNulo() {
        assertThrows(RuntimeException.class,
                () -> new ClienteId(null));
    }
}