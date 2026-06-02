package co.edu.udc.desechos_fabrica.location.domain.valueobject;

import co.edu.udc.desechos_fabrica.location.domain.valueobjects.LocationName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class LocationNameTest {

    @Test
    @DisplayName("Should create a valid LocationName")
    public void testValidLocationNameCreation() {
        final String validName = "Valid Location";
        final LocationName locationName = new LocationName(validName);
        assertEquals(validName, locationName.locationName());
    }
}
