package co.edu.udc.desechos_fabrica.location.application.service;

import co.edu.udc.desechos_fabrica.location.application.port.in.CreateLocationUseCase;
import co.edu.udc.desechos_fabrica.location.application.port.out.GetLocationByIdPort;
import co.edu.udc.desechos_fabrica.location.application.port.out.SaveLocationPort;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class CreateLocationService implements CreateLocationUseCase {

    private final SaveLocationPort saveLocationPort;
    private final GetLocationByIdPort getLocationByIdPort;
    private final Validator validator;



}
