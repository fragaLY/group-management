package gm.vk.exceptions.data.contacts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) public class ContactsNotFoundException
    extends RuntimeException {

  public ContactsNotFoundException() {
  }

  public ContactsNotFoundException(final String message) {
    super(message);
  }
}
