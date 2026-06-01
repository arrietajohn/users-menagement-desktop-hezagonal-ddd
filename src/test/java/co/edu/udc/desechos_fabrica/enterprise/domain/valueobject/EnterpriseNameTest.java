package co.edu.udc.desechos_fabrica.enterprise.domain.valueobject;

import co.edu.udc.desechos_fabrica.enterprise.domain.exception.InvalidEnterpriseNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EnterpriseNameTest {

    @Test
    @DisplayName("Should create an EnterpriseName with valid name")
    public void testValidEnterpriseNameCreation(){
        final String name = "ECORESIDUOS S.A";
        assertDoesNotThrow(() -> new EnterpriseName(name));
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"", "  ", "\t", "\n", "\r", "\f", "\b", "EC", "RE  ", "", "   SA ", "OS\t"})
    @DisplayName("should throw InvalidEnterpriseNameException for empty names or names shorter than 3 characters")
    public void testShortEnterpriseNameCreation(final String enterpriseName){
        assertThrows(InvalidEnterpriseNameException.class, () -> new EnterpriseName(enterpriseName));
    }

    @Test
    @DisplayName("Should throw NullPointerException with null name")
    public void testNullEnterpriseNameCreation(){
        assertThrows(NullPointerException.class, () -> new EnterpriseName(null));
    }

}
