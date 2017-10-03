package gm.vk.exceptions.person.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonRoleNotFoundException extends RuntimeException {

  public PersonRoleNotFoundException() {}

  public PersonRoleNotFoundException(final String message) {
    super(message);
  }
}
