package gm.vk.exceptions.data.contacts.address;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException() {
    }

  public AddressNotFoundException(final String message) {
    super(message);
  }
}
