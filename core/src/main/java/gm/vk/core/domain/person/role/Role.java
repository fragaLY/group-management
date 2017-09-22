package gm.vk.core.domain.person.role;

import java.util.Arrays;
import java.util.Optional;

enum Role {

    LECTURER("LECTURER"), STUDENT("STUDENT"), ADMIN("ADMIN");

    Role(final String role) {
        this.role = role;
    }

    private String role;

    public String getRole() {
        return role;
    }

    public static Optional<Role> getPersonTypeByType(final String _role) {
        return Arrays.stream(values()).parallel().filter(person -> person.getRole().equals(_role)).findFirst();
    }

}
