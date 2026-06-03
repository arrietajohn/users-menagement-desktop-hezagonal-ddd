package co.edu.udc.desechos_fabrica.location.domain.model;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseId;
import co.edu.udc.desechos_fabrica.location.domain.valueobjects.*;
import co.edu.udc.desechos_fabrica.location.domain.enums.*;
import lombok.Value;
import lombok.NonNull;

@Value
public class LocationModel {

    LocationId id;
    @NonNull LocationName name;
    @NonNull LocationCity city;
    @NonNull EnterpriseId enterpriseId;
    @NonNull LocationState state;
    @NonNull LocationCountry country;
    @NonNull LocationCoordinate coordinate;
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

    public LocationModel updateWith(
            final LocationName newName,
            final LocationCity newCity,
            final LocationState newState,
            final LocationCountry newCountry,
            final LocationCoordinate newCoordinate) {
        return new LocationModel(id, newName, newCity, enterpriseId, newState, newCountry, newCoordinate, status);
    }

    public LocationModel activate() {
        return new LocationModel(id, name, city, enterpriseId, state, country, coordinate, LocationStatus.ACTIVE);
    }

    public LocationModel deactivate() {
        return new LocationModel(id, name, city, enterpriseId, state, country, coordinate, LocationStatus.INACTIVE);
    }

}
