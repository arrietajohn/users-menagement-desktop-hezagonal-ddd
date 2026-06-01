package co.edu.udc.desechos_fabrica.enterprise.domain.factory;

import co.edu.udc.desechos_fabrica.enterprise.domain.enums.EnterpriseStatus;
import co.edu.udc.desechos_fabrica.enterprise.domain.model.EnterpriseModel;
import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseName;
import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseNit;

public class EnterpriseFactory {

    public static EnterpriseModel create(
            final EnterpriseNit nit,
            final EnterpriseName name) {
        return new EnterpriseModel(nit, name, EnterpriseStatus.PENDING);
    }
}
