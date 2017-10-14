package gm.vk.controllers.exceptions.custom.subject.examination.grade;

import com.google.common.collect.Sets;
import gm.vk.controllers.exceptions.error.ErrorDetails;
import gm.vk.exceptions.subject.examination.grade.GradeNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("gradeExceptionHandler")
public class GradeExceptionHandler {

    /**
     * Handle the 404 exception. Grade Not Found.
     *
     * @param ex - exception
     * @return {@link ResponseEntity} object
     */
    @ExceptionHandler({GradeNotFoundException.class})
    public ResponseEntity<Object> handleGradeNotFoundException(final GradeNotFoundException ex) {

        final ErrorDetails error =
                new ErrorDetails.Builder()
                        .setStatus(HttpStatus.NOT_FOUND)
                        .setOutputMessage(ex.getLocalizedMessage())
                        .setErrors(Sets.newHashSet("Grade Not Found"))
                        .build();

        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }
}
