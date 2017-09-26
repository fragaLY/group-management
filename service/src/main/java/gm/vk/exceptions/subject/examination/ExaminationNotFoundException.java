package gm.vk.exceptions.subject.examination;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExaminationNotFoundException extends RuntimeException {

    public ExaminationNotFoundException() {
    }

    public ExaminationNotFoundException(String message) {
        super(message);
    }
}