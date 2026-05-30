package co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.cli.util;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
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
}
