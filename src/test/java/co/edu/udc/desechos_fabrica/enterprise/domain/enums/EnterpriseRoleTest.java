package co.edu.udc.desechos_fabrica.enterprise.domain.enums;

import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class EnterpriseRoleTest {

    @ParameterizedTest
    @ValueSource(strings = {"PRODUCER", "TRANSPORTER", "RECEPTOR"})
    @DisplayName("Should return correct EnterpriseRole for valid input")
    void shouldReturnCorrectEnterpriseRoleForValidInput(String roleStr) {
        EnterpriseRole role = EnterpriseRole.fromString(roleStr);
        assertNotNull(role);
        assertEquals(roleStr.toUpperCase(), role.name());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", "\f", "\b", "INVALID_ROLE"})
    @DisplayName("Should throw InvalidEnterpriseRoleException when input is empty or invalid")
    void shouldThrowInvalidEnterpriseRoleExceptionWhenInputIsEmptyOrInvalid(String roleStr) {
        assertThrows(InvalidEnterpriseRoleException.class, () -> EnterpriseRole.fromString(roleStr));
    }
}
