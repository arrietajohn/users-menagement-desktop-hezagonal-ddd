package co.edu.udc.desechos_fabrica.enterprise.domain;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseNit;
import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseNitException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;



public class EnterpriseNitTest {

    @Test
    @DisplayName("Should create an EnterpriseNit with valid input")
    public void testValidEnterpriseNitCreation() {
        String validNit = "1234567890";
        assertDoesNotThrow(() -> new EnterpriseNit(validNit));
    }

    @Test
    @DisplayName("Should throw InvalidEnterpriseNitException with invalid input")
    public void testInvalidEnterpriseNitCreation() {
        String invalidNit = "12345";
        assertThrows(InvalidEnterpriseNitException.class, () -> new EnterpriseNit(invalidNit));
    }

}
