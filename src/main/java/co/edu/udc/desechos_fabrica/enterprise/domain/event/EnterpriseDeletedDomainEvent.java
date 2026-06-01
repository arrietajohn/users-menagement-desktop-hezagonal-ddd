package co.edu.udc.desechos_fabrica.enterprise.domain.event;

import co.edu.udc.desechos_fabrica.shared.DomainEvent;
import co.edu.udc.desechos_fabrica.enterprise.domain.model.EnterpriseModel;
import java.util.Map;
import lombok.Getter;

@Getter
public class EnterpriseDeletedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "enterprise.deleted";

    private final EnterpriseModel enterprise;

    public EnterpriseDeletedDomainEvent(final EnterpriseModel enterprise) {
        super(EVENT_NAME);
        this.enterprise = enterprise;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "nit", enterprise.getNit().value(),
                "name", enterprise.getName().value(),
                "status", enterprise.getStatus().name());
    }
}
