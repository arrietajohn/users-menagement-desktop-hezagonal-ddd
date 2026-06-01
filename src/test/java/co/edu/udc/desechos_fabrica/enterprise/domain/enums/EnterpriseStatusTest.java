package co.edu.udc.desechos_fabrica.enterprise.domain.enums;

import org.junit.jupiter.api.DisplayName;
import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseStatusException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class EnterpriseStatusTest {

    @ParameterizedTest
    @ValueSource(strings = {"ACTIVE", "INACTIVE", "PENDING", "BLOCKED"})
    @DisplayName("Should return the correct EnterpriseStatus for valid input")
    public void testValidEnterpriseStatus(final String status) {
        assertEquals(EnterpriseStatus.valueOf(status), EnterpriseStatus.fromString(status));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", "\f", "\b", "INVALID_STATUS"})
    @DisplayName("Should throw InvalidEnterpriseStatusException when input is empty or invalid")
    public void testInvalidEnterpriseStatus(final String status) {
        assertThrows(InvalidEnterpriseStatusException.class, () -> EnterpriseStatus.fromString(status));
    }
}
