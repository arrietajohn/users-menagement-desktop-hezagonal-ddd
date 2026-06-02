package co.edu.udc.desechos_fabrica.location.domain.valueobject;

import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationNameException;
import co.edu.udc.desechos_fabrica.location.domain.valueobjects.LocationName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class LocationAddressTest {

    @Test
    @DisplayName("Should create a valid LocationAddress")
    public void testValidLocationAddressCreation() {
        final String validAddress = "Valid Address";
            final LocationName locationAddress = new LocationName(validAddress);
        assertEquals(validAddress, locationAddress.locationName());
    }

    @Test
    @DisplayName("Should throw exception when creating LocationAddress with value shorter than minimum length")
    public void testShortLocationAddressCreation() {
        final String shortAddress = "ab";
        assertThrows(InvalidLocationNameException.class, () -> new LocationName(shortAddress));
    }

    @ParameterizedTest
    @DisplayName("Should throw exception when creating LocationAddress with empty value")
    @ValueSource(strings = {"", " "})
    public void testEmptyLocationAddressCreation(String emptyAddress) {
        assertThrows(InvalidLocationNameException.class, () -> new LocationName(emptyAddress));
    }
}
