package gm.vk.exceptions.group;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) public class SemesterNotFoundException
    extends RuntimeException {

  public SemesterNotFoundException() {
  }

  public SemesterNotFoundException(String message) {
    super(message);
  }
}
