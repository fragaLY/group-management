package gm.vk.core.domain.person.role;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
  LECTURER("LECTURER"),
  STUDENT("STUDENT"),
  ADMIN("ADMIN"),
  SURFER("SURFER");

  private String role;

  Role(final String role) {
    this.role = role;
  }

  public static Optional<Role> getPersonTypeByType(final String _role) {
    return Arrays.stream(values())
        .parallel()
        .filter(person -> person.getRole().equals(_role))
        .findFirst();
  }

  public String getRole() {
    return role;
  }
}
