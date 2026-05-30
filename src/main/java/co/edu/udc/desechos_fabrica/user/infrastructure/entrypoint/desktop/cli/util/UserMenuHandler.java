package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.util;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserMenuHandler {

    private final ConsoleIO console;

    public UserRole selectRoleFromConsole() {
        while (true) {
            console.println("\nPlease select a role:");
            console.println("1. ADMIN");
            console.println("2. REVIEWER");
            console.println("3. ENTERPRISE_ADMIN");
            console.println("4. MEMBER");

            final int choice = console.readInt("Enter the role number: ");
            final Optional<UserRole> roleOptional = getRoleByNumber(choice);

            if (roleOptional.isPresent()) {
                return roleOptional.get();
            } else {
                console.println("\n  Error: Invalid selection. Please try again.");
            }
        }
    }

    private Optional<UserRole> getRoleByNumber(final int number) {
        switch (number) {
            case 1:
                return Optional.of(UserRole.ADMIN);
            case 2:
                return Optional.of(UserRole.REVIEWER);
            case 3:
                return Optional.of(UserRole.ENTERPRISE_ADMIN);
            case 4:
                return Optional.of(UserRole.MEMBER);
            default:
                return Optional.empty();
        }
    }

    public UserStatus selectStatusFromConsole() {
        while (true) {
            console.println("\nPlease select a new status:");
            console.println("1. ACTIVE");
            console.println("2. INACTIVE");
            console.println("3. PENDING");
            console.println("4. BLOCKED");

            final int choice = console.readInt("Enter the status number: ");
            final Optional<UserStatus> statusOptional = getStatusByNumber(choice);

            if (statusOptional.isPresent()) {
                return statusOptional.get();
            } else {
                console.println("\n  Error: Invalid selection. Please try again.");
            }
        }
    }

    private Optional<UserStatus> getStatusByNumber(final int number) {
        switch (number) {
            case 1: return Optional.of(UserStatus.ACTIVE);
            case 2: return Optional.of(UserStatus.INACTIVE);
            case 3: return Optional.of(UserStatus.PENDING);
            case 4: return Optional.of(UserStatus.BLOCKED);
            default: return Optional.empty();
        }
    }
}

