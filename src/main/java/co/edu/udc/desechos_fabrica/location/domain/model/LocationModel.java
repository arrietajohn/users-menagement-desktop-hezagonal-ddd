package co.edu.udc.desechos_fabrica.location.domain.model;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseId;
import co.edu.udc.desechos_fabrica.location.domain.valueobjects.*;
import co.edu.udc.desechos_fabrica.location.domain.enums.*;
import lombok.Value;

@Value
public class LocationModel {

    LocationId id;
    LocationName name;
    LocationCity city;
    EnterpriseId enterpriseId;
    LocationState state;
    LocationCountry country;
    LocationCoordinate coordinate;
    LocationStatus status;

    public static LocationModel create(
            final LocationId id,
            final LocationName name,
            final LocationCity city,
            final EnterpriseId enterpriseId,
            final LocationState state,
            final LocationCountry country,
            final LocationCoordinate coordinate) {
        return new LocationModel(id, name, city, enterpriseId, state, country, coordinate, LocationStatus.ACTIVE);
    }

}
