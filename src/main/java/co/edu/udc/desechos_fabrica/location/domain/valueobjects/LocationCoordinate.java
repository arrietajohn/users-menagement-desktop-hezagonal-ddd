package co.edu.udc.desechos_fabrica.location.domain.valueobjects;

import co.edu.udc.desechos_fabrica.location.domain.exception.InvalidLocationCoordinateException;


public record LocationCoordinate(Double latitude, Double longitude){

    public LocationCoordinate {
        validateNotEmpty(latitude, longitude);
        validateFormat(latitude, longitude);
    }

    private static void validateNotEmpty(final Double latitude, final Double longitude) {
        if (latitude==null || longitude==null) {
            throw InvalidLocationCoordinateException.becauseValueIsEmpty();
        }
    }

    private static void validateFormat(final Double latitude, final Double longitude) {
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw InvalidLocationCoordinateException.becauseInvalidFormat();
        }
    }

    @Override
    public String toString() {
        return String.format("Lat: %f, Lon: %f", latitude, longitude);
    }
}
