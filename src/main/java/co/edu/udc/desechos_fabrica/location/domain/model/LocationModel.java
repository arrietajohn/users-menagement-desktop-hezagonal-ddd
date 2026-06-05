package co.edu.udc.desechos_fabrica.location.domain.model;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseId;
import co.edu.udc.desechos_fabrica.location.domain.valueobject.*;
import co.edu.udc.desechos_fabrica.location.domain.enums.*;
import lombok.Value;
import lombok.NonNull;

@Value
public class LocationModel {

    LocationId id;
    @NonNull LocationName name;
    @NonNull LocationAddress address;
    @NonNull EnterpriseId enterpriseId;
    @NonNull LocationCountry country;
    @NonNull LocationState state;
    @NonNull LocationCity city;
    @NonNull LocationCoordinate coordinate;
    LocationStatus status;

    public static LocationModel create(
            final LocationName name,
            final LocationAddress address,
            final EnterpriseId enterpriseId,
            final LocationCountry country,
            final LocationState state,
            final LocationCity city,
            final LocationCoordinate coordinate) {
        return new LocationModel(null, name, address, enterpriseId, country, state, city, coordinate, LocationStatus.ACTIVE);
    }

    public LocationModel updateWith(
            final LocationName newName,
            final LocationAddress newAddress,
            final LocationCity newCity,
            final LocationState newState,
            final LocationCountry newCountry,
            final LocationCoordinate newCoordinate) {
        return new LocationModel(id, newName, newAddress, enterpriseId, newCountry, newState, newCity, newCoordinate, status);
    }

    public LocationModel activate() {
        return new LocationModel(id, name, address, enterpriseId, country, state, city, coordinate, LocationStatus.ACTIVE);
    }

    public LocationModel deactivate() {
        return new LocationModel(id, name, address, enterpriseId, country, state, city, coordinate, LocationStatus.INACTIVE);
    }

}
