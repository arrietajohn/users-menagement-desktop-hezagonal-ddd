package co.edu.udc.desechos_fabrica.location.domain.event;

import co.edu.udc.desechos_fabrica.location.domain.model.LocationModel;
import co.edu.udc.desechos_fabrica.shared.DomainEvent;

import java.io.Serializable;
import java.util.Map;

public class LocationCreatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "location.updated";

    private final LocationModel location;

    public LocationCreatedDomainEvent(LocationModel location) {
        super(EVENT_NAME);
        this.location = location;
    }

    @Override
    public Map<String, Serializable> payload() {
        return java.util.Map.of(
                "locationId", location.getId().value(),
                "name", location.getName().value(),
                "enterpriseId", location.getEnterpriseId().value(),
                "city", location.getCity().value(),
                "coordinate", location.getCoordinate().toString(),
                "status", location.getStatus().name()
        );
    }
}
