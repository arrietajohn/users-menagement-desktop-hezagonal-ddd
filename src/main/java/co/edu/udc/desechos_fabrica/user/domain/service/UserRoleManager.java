package co.edu.udc.desechos_fabrica.user.domain.service;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;


public interface UserRoleManager {
    /**
     * Revisa si un actor tiene permiso para actualizar a otro usuario.
     *
     * @param actor      El usuario que está realizando la acción.
     * @param targetUser El usuario que va a ser modificado.
     * @param newRole    El nuevo rol que se le quiere asignar al targetUser.
     */
    void checkUpdatePermissions(UserModel actor, UserModel targetUser, UserRole newRole);

    void checkDeletePermissions(UserModel actor, UserModel targetUser);

}
