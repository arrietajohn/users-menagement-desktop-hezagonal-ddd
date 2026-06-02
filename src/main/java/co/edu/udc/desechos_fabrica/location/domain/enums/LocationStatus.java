package co.edu.udc.desechos_fabrica.location.domain.enums;

import lombok.Getter;

@Getter
public enum LocationStatus {

    ACTIVE(1),
    INACTIVE(2);

    private final int value;

    LocationStatus(int value) {
        this.value = value;
    }
}
