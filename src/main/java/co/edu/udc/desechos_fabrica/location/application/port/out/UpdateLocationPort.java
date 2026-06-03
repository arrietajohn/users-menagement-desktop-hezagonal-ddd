package co.edu.udc.desechos_fabrica.location.application.port.out;

import co.edu.udc.desechos_fabrica.location.domain.model.LocationModel;

public interface UpdateLocationPort {
    LocationModel update(LocationModel location);
}
