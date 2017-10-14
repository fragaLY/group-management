package gm.vk.controllers.exceptions.custom.group.faculty;

import com.google.common.collect.Sets;
import gm.vk.controllers.exceptions.error.ErrorDetails;
import gm.vk.exceptions.group.faculty.FacultyNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice("facultyExceptionHandler")
public class FacultyExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle the 404 exception. Faculty Not Found.
     *
     * @param ex - exception
     * @return {@link ResponseEntity} object
     */
    @ExceptionHandler({FacultyNotFoundException.class})
    public ResponseEntity<Object> handleFacultyNotFoundException(final FacultyNotFoundException ex) {

        final ErrorDetails error =
                new ErrorDetails.Builder()
                        .setStatus(HttpStatus.NOT_FOUND)
                        .setOutputMessage(ex.getLocalizedMessage())
                        .setErrors(Sets.newHashSet("Faculty Not Found"))
                        .build();

        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }
}
