package co.edu.udc.desechos_fabrica.user.domain.service;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.exception.PermissionDeniedException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import java.util.Objects;

public class UserRoleManagerService implements UserRoleManager {

    @Override
    public void checkUpdatePermissions(UserModel actor, UserModel targetUser, UserRole newRole) {

        if (newRole != null && newRole != targetUser.getRole()) {
            if (newRole.getLevel() >= actor.getRole().getLevel()) {
                throw new PermissionDeniedException("You cannot assign a role that is equal to or higher than your own.");
            }
        }
        validateAccessToTarget(actor, targetUser);
    }

    @Override
    public void checkDeletePermissions(UserModel actor, UserModel targetUser) {
        validateAccessToTarget(actor, targetUser);
    }

    private void validateAccessToTarget(UserModel actor, UserModel targetUser) {

        final UserRole actorRole = actor.getRole();
        final UserRole targetRole = targetUser.getRole();
        final UserStatus actorStatus = actor.getStatus();

        switch (actorStatus) {
            case INACTIVE -> throw new PermissionDeniedException("Your account is inactive.");
            case PENDING -> throw new PermissionDeniedException("Your account is pending approval. You cannot perform this action until your account is active.");
            case BLOCKED -> throw new PermissionDeniedException("Your account is blocked.");
        }

        if (actorRole == UserRole.ADMIN) return;

        switch (targetRole) {
            case ADMIN ->
                    throw new PermissionDeniedException("Only an ADMIN can modify/delete a other ADMIN.");
            case REVIEWER ->
                    throw new PermissionDeniedException("Only an ADMIN can modify/delete a REVIEWER.");
            case ENTERPRISE_ADMIN -> {
                if (actorRole != UserRole.REVIEWER)
                    throw new PermissionDeniedException("Insufficient permissions.");
            }
            case MEMBER -> {
                if (actorRole == UserRole.ENTERPRISE_ADMIN &&
                        !Objects.equals(actor.getEnterpriseId(), targetUser.getEnterpriseId())) {
                    throw new PermissionDeniedException("Scope restricted to your enterprise.");
                }
                if (actorRole == UserRole.MEMBER)
                    throw new PermissionDeniedException("MEMBER cannot perform this action.");
            }
        }
    }
}
