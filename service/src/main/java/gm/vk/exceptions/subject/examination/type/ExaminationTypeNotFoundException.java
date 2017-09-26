package gm.vk.exceptions.subject.examination.type;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExaminationTypeNotFoundException extends RuntimeException {

    public ExaminationTypeNotFoundException() {
    }

    public ExaminationTypeNotFoundException(final String message) {
        super(message);
    }
}