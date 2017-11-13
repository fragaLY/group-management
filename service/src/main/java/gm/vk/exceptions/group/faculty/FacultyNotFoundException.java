package gm.vk.exceptions.group.faculty;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FacultyNotFoundException extends RuntimeException {

    public FacultyNotFoundException() {
    }

    public FacultyNotFoundException(String message) {
        super(message);
    }
}
