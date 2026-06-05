package co.edu.udc.desechos_fabrica.location.domain.valueobject;

import co.edu.udc.desechos_fabrica.location.domain.valueobjects.LocationName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationNameException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LocationNameTest {

    @Test
    @DisplayName("Should create a valid LocationName")
    public void testValidLocationNameCreation() {
        final String validName = "Valid Location";
        final LocationName locationName = new LocationName(validName);
        assertEquals(validName, locationName.locationName());
    }

    @Test
    @DisplayName("Should throw exception when creating LocationName with value shorter than minimum length")
    public void testShortLocationNameCreation() {
        final String shortName = "ab";
        assertThrows(InvalidLocationNameException.class, () -> new LocationName(shortName));
    }

    @ParameterizedTest
    @DisplayName("Should throw exception when creating LocationName with empty value")
    @ValueSource (strings = {"", " "})
    public void testEmptyLocationNameCreation(String emptyName) {
        assertThrows(InvalidLocationNameException.class, () -> new LocationName(emptyName));
    }

}
