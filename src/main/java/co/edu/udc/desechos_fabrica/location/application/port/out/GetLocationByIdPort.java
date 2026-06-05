package co.edu.udc.desechos_fabrica.location.application.port.out;

import co.edu.udc.desechos_fabrica.location.domain.model.LocationModel;
import co.edu.udc.desechos_fabrica.location.domain.valueobjects.LocationId;
import java.util.Optional;

public interface GetLocationByIdPort {
    Optional<LocationModel> getById(LocationId id);
}
