package co.edu.udc.desechos_fabrica.enterprise.domain.model;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseName;
import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseNit;
import co.edu.udc.desechos_fabrica.enterprise.domain.enums.EnterpriseStatus;
import lombok.Value;

@Value
public class EnterpriseModel {

    EnterpriseNit nit;
    EnterpriseName name;
    EnterpriseStatus status;

    public static EnterpriseModel create(
        final EnterpriseNit nit,
        final EnterpriseName name) {
        return new EnterpriseModel(nit, name, EnterpriseStatus.PENDING);
    }
    
}
