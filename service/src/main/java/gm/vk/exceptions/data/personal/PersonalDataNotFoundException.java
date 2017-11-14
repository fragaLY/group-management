package gm.vk.exceptions.data.personal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) public class PersonalDataNotFoundException
    extends RuntimeException {

  public PersonalDataNotFoundException() {
  }

  public PersonalDataNotFoundException(final String message) {
    super(message);
  }
}
