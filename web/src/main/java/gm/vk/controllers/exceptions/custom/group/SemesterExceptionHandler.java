package gm.vk.controllers.exceptions.custom.group;

import com.google.common.collect.Sets;
import gm.vk.controllers.exceptions.error.ErrorDetails;
import gm.vk.exceptions.group.SemesterNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice("semesterExceptionHandler")
public class SemesterExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle the 404 exception. Semester Not Found.
     *
     * @param ex - exception
     * @return {@link ResponseEntity} object
     */
    @ExceptionHandler({SemesterNotFoundException.class})
    public ResponseEntity<Object> handleSemesterNotFoundException(final SemesterNotFoundException ex) {

        final ErrorDetails error =
                new ErrorDetails.Builder()
                        .setStatus(HttpStatus.NOT_FOUND)
                        .setOutputMessage(ex.getLocalizedMessage())
                        .setErrors(Sets.newHashSet("Semester Not Found"))
                        .build();

        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }
}
