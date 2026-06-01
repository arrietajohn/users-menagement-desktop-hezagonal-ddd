package co.edu.udc.desechos_fabrica.enterprise.domain.valueobject;

import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class EnterpriseNameTest {

    @Test
    @DisplayName("Should create an EnterpriseName with valid name")
    public void testValidEnterpriseNameCreation(){
        final String name = "ECORESIDUOS S.A";
        assertDoesNotThrow(() -> new EnterpriseName(name));
    }

    @Test
    @DisplayName("Should throw InvalidEnterpriseNameException with invalid name")
    public void testInvalidEnterpriseNameCreation(){
        final String invalidName = "";
        assertThrows(InvalidEnterpriseNameException.class, () -> new EnterpriseName(invalidName));
    }
}
