package co.edu.udc.desechos_fabrica.enterprise.domain.model;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseName;
import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseNit;
import co.edu.udc.desechos_fabrica.enterprise.domain.enums.EnterpriseStatus;
import co.edu.udc.desechos_fabrica.enterprise.domain.enums.EnterpriseRole;
import lombok.Value;

@Value
public class EnterpriseModel {

    EnterpriseNit nit;
    EnterpriseName name;
    EnterpriseStatus status;
    EnterpriseRole role;


    public EnterpriseModel(EnterpriseNit nit, EnterpriseName name, EnterpriseStatus status, EnterpriseRole role){
        this.nit = nit;
        this.name = name;
        this.status = status;
        this.role = role;
    }

    public EnterpriseModel activate() {
        return new EnterpriseModel(nit, name, EnterpriseStatus.ACTIVE, role);
    }

    public EnterpriseModel deactivate() {
        return new EnterpriseModel(nit, name, EnterpriseStatus.INACTIVE, role);
    }

    public EnterpriseModel updateWith(EnterpriseName newName, EnterpriseStatus newStatus) {
        return new EnterpriseModel(nit, newName, newStatus, role);
    }

}
