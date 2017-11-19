package gm.vk.exceptions.group;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException() {
    }

  public GroupNotFoundException(String message) {
    super(message);
  }
}
