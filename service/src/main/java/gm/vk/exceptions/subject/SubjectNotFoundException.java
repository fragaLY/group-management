package gm.vk.exceptions.subject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) public class SubjectNotFoundException extends RuntimeException {

  public SubjectNotFoundException() {
  }

  public SubjectNotFoundException(final String message) {
    super(message);
  }
}
