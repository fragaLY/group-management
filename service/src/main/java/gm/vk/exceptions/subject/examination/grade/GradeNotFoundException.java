package gm.vk.exceptions.subject.examination.grade;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GradeNotFoundException extends RuntimeException {

    public GradeNotFoundException() {
    }

  public GradeNotFoundException(final String message) {
    super(message);
  }
}
