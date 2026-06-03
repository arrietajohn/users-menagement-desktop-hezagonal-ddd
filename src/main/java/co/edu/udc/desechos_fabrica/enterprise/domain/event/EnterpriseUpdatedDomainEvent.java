package co.edu.udc.desechos_fabrica.enterprise.domain.event;

import co.edu.udc.desechos_fabrica.shared.DomainEvent;
import co.edu.udc.desechos_fabrica.enterprise.domain.model.EnterpriseModel;

import java.io.Serializable;
import java.util.Map;
import lombok.Getter;

@Getter
public class EnterpriseUpdatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "enterprise.updated";

    private final EnterpriseModel enterprise;

    public EnterpriseUpdatedDomainEvent(final EnterpriseModel enterprise) {
        super(EVENT_NAME);
        this.enterprise = enterprise;
    }

    @Override
    public Map<String, Serializable> payload() {
        return Map.of(
                "nit", enterprise.getNit().value(),
                "name", enterprise.getName().value(),
                "status", enterprise.getStatus().name());
    }
}
