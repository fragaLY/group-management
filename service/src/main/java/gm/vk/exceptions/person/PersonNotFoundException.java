package gm.vk.exceptions.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException() {
    }

    public PersonNotFoundException(final String message) {
        super(message);
    }
}