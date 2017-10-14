package gm.vk.controllers.exceptions.custom.subject.examination;

import com.google.common.collect.Sets;
import gm.vk.controllers.exceptions.error.ErrorDetails;
import gm.vk.exceptions.subject.examination.ExaminationNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice("examinationExceptionHandler")
public class ExaminationExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle the 404 exception. Examination Not Found.
     *
     * @param ex - exception
     * @return {@link ResponseEntity} object
     */
    @ExceptionHandler({ExaminationNotFoundException.class})
    public ResponseEntity<Object> handleExaminationNotFoundException(
            final ExaminationNotFoundException ex) {

        final ErrorDetails error =
                new ErrorDetails.Builder()
                        .setStatus(HttpStatus.NOT_FOUND)
                        .setOutputMessage(ex.getLocalizedMessage())
                        .setErrors(Sets.newHashSet("Examination Not Found"))
                        .build();

        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }
}
