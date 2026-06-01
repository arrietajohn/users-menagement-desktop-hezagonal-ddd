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

    public EnterpriseModel(EnterpriseNit nit, EnterpriseName name, EnterpriseStatus status){
        this.nit = nit;
        this.name = name;
        this.status = status;
    }

    public EnterpriseModel activate() {
        return new EnterpriseModel(nit, name, EnterpriseStatus.ACTIVE);
    }

    public EnterpriseModel deactivate() {
        return new EnterpriseModel(nit, name, EnterpriseStatus.INACTIVE);
    }

}
