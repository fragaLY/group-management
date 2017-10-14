package gm.vk.controllers.exceptions.custom.subject.examination.type;

import com.google.common.collect.Sets;
import gm.vk.controllers.exceptions.error.ErrorDetails;
import gm.vk.exceptions.subject.examination.type.ExaminationTypeNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("examinationTypeExceptionHandler")
public class ExaminationTypeExceptionHandler {

    /**
     * Handle the 404 exception. ExaminationType Not Found.
     *
     * @param ex - exception
     * @return {@link ResponseEntity} object
     */
    @ExceptionHandler({ExaminationTypeNotFoundException.class})
    public ResponseEntity<Object> handleExaminationTypeNotFoundException(
            final ExaminationTypeNotFoundException ex) {

        final ErrorDetails error =
                new ErrorDetails.Builder()
                        .setStatus(HttpStatus.NOT_FOUND)
                        .setOutputMessage(ex.getLocalizedMessage())
                        .setErrors(Sets.newHashSet("Examination Type Not Found"))
                        .build();

        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }
}
