package co.edu.udc.desechos_fabrica.location.application.service.mapper;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseId;
import co.edu.udc.desechos_fabrica.location.application.service.dto.command.CreateLocationCommand;
import co.edu.udc.desechos_fabrica.location.domain.model.LocationModel;
import co.edu.udc.desechos_fabrica.location.domain.valueobject.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LocationApplicationMapper {

    public LocationModel fromCreateCommandToModel(CreateLocationCommand command) {
        return LocationModel.create(
                new LocationName(command.name()),
                new LocationAddress(command.address()),
                new EnterpriseId(command.enterpriseId()),
                new LocationCountry(command.country()),
                new LocationState(command.state()),
                new LocationCity(command.city()),
                new LocationCoordinate(command.coordinate().latitude(), command.coordinate().longitude()));
    }
}
