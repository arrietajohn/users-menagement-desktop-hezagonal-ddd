package co.edu.udc.desechos_fabrica.location.domain.valueobject;

import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationIdException;
import co.edu.udc.desechos_fabrica.location.domain.valueobjects.LocationId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


public class LocationIdTest {

    @Test
    @DisplayName("Should create a valid LocationId")
    public void testValidLocationIdCreation() {
        final String validId = "123";
        final LocationId locationId = new LocationId(validId);
        assertEquals(validId, locationId.value());
    }

    @Test
    @DisplayName("Should throw exception when creating LocationId with empty value")
    public void testEmptyLocationIdCreation() {
        final String emptyId = "   ";
        assertThrows(InvalidLocationIdException.class, () -> new LocationId(emptyId));
    }

    @Test
    @DisplayName("Should throw exception when creating LocationId with invalid format")
    public void testInvalidLocationIdFormat() {
        final String invalidId = "Abc";
        assertThrows(InvalidLocationIdException.class, () -> new LocationId(invalidId));
    }




}
