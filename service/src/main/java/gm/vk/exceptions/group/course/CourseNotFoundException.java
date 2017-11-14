package gm.vk.exceptions.group.course;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) public class CourseNotFoundException extends RuntimeException {

  public CourseNotFoundException() {
  }

  public CourseNotFoundException(String message) {
    super(message);
  }
}
