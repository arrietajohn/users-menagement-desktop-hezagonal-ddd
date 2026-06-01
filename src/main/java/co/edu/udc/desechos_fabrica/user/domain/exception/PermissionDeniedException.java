package co.edu.udc.desechos_fabrica.user.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public class PermissionDeniedException extends DomainException {

    private static final String MESSAGE_PERMISSION_UPDATED = "Note: You cannot change the user role. It will remain unchanged.";
    private static final String MESSAGE_PERMISSION_DELETED = "Note: You cannot delete the user. It will remain unchanged.";

    public PermissionDeniedException(String message) {
        super(message);
    }

    public static PermissionDeniedException becauseHaveNotUpdatePermission(final String email) {
        return new PermissionDeniedException(MESSAGE_PERMISSION_UPDATED);
    }

    public static PermissionDeniedException becauseHaveNotDeletePermission(final String email) {
        return new PermissionDeniedException(MESSAGE_PERMISSION_DELETED);
    }
}
