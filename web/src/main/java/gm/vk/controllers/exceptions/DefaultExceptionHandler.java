package gm.vk.controllers.exceptions;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import gm.vk.controllers.exceptions.error.ErrorDetails;
import gm.vk.exceptions.data.contacts.ContactsNotFoundException;
import gm.vk.exceptions.data.contacts.address.AddressNotFoundException;
import gm.vk.exceptions.data.personal.PersonalDataNotFoundException;
import gm.vk.exceptions.group.GroupNotFoundException;
import gm.vk.exceptions.group.SemesterNotFoundException;
import gm.vk.exceptions.group.course.CourseNotFoundException;
import gm.vk.exceptions.group.faculty.FacultyNotFoundException;
import gm.vk.exceptions.person.PersonNotFoundException;
import gm.vk.exceptions.person.role.PersonRoleNotFoundException;
import gm.vk.exceptions.subject.SubjectNotFoundException;
import gm.vk.exceptions.subject.examination.ExaminationNotFoundException;
import gm.vk.exceptions.subject.examination.grade.GradeNotFoundException;
import gm.vk.exceptions.subject.examination.type.ExaminationTypeNotFoundException;
import gm.vk.exceptions.user.UserNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String MESSAGE_NOT_FOUND = "Page Not Found";

  /**
   * Handles the 404 exception. Page not found. Handler not found.
   *
   * @param ex - exception
   * @param headers - headers
   * @param status - status
   * @param req - request
   * @return {@link ResponseEntity} object
   */
  @Override protected ResponseEntity<Object> handleNoHandlerFoundException(
      final NoHandlerFoundException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest req) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        ImmutableSet.of(MESSAGE_NOT_FOUND)).build();

    return handleExceptionInternal(ex, error, headers, status, req);
  }

  /**
   * Handles the 409 exception. MethodArgumentNotValid.
   *
   * @param ex - exception
   * @param headers - headers
   * @param status - status
   * @param request - request
   * @return {@link ResponseEntity} object
   */
  @Override protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    final Set<String> errors = Sets.newHashSet();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    });

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.CONFLICT).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        errors).build();

    return handleExceptionInternal(ex, error, headers, error.getStatus(), request);
  }

  /**
   * Handle the 400 exception. Bad request.
   *
   * @param ex - exception
   * @param headers - headers
   * @param status - status
   * @param request - request
   * @return {@link ResponseEntity} object
   */
  @Override protected ResponseEntity<Object> handleBindException(
      final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

    final Set<String> errors = Sets.newConcurrentHashSet();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    });

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.BAD_REQUEST).setOutputMessage(
        ex.getLocalizedMessage()).setErrors(errors).build();

    return handleExceptionInternal(ex, error, headers, error.getStatus(), request);
  }

  /**
   * Handle the 400 exception. Bad request.
   *
   * @param ex - exception
   * @param headers - headers
   * @param status - status
   * @param request - request
   * @return {@link ResponseEntity} object
   */
  @Override protected ResponseEntity<Object> handleTypeMismatch(
      final TypeMismatchException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    final String exception = ex.getValue().toString()
                             + " value for "
                             + ex.getPropertyName()
                             + " should be of type "
                             + ex.getRequiredType();

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.BAD_REQUEST).setOutputMessage(
        ex.getLocalizedMessage()).setErrors(Sets.newHashSet(exception)).build();

    return new ResponseEntity<>(error, headers, error.getStatus());
  }

  /**
   * Handle the 400 exception. Bad request.
   *
   * @param ex - exception
   * @param headers - headers
   * @param status - status
   * @param request - request
   * @return {@link ResponseEntity} object
   */
  @Override protected ResponseEntity<Object> handleMissingServletRequestPart(
      final MissingServletRequestPartException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    final String exception = ex.getRequestPartName() + " part is missing";

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.BAD_REQUEST).setOutputMessage(
        ex.getLocalizedMessage()).setErrors(Sets.newHashSet(exception)).build();

    return new ResponseEntity<>(error, headers, error.getStatus());
  }

  /**
   * Handle the 400 exception. Bad request.
   *
   * @param ex - exception
   * @param headers - headers
   * @param status - status
   * @param request - request
   * @return {@link ResponseEntity} object
   */
  @Override protected ResponseEntity<Object> handleMissingServletRequestParameter(
      final MissingServletRequestParameterException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    final String exception = ex.getParameterName() + " parameter is missing";

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.BAD_REQUEST).setOutputMessage(
        ex.getLocalizedMessage()).setErrors(Sets.newHashSet(exception)).build();

    return new ResponseEntity<>(error, headers, error.getStatus());
  }

  /**
   * Handle the 400 exception. Bad request.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ MethodArgumentTypeMismatchException.class }) public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      final MethodArgumentTypeMismatchException ex) {

    final String exception = ex.getName() + " should be of type " + ex.getRequiredType().getName();

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.BAD_REQUEST).setOutputMessage(
        ex.getLocalizedMessage()).setErrors(Sets.newHashSet(exception)).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 400 exception. Bad request.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ ConstraintViolationException.class }) public ResponseEntity<Object> handleConstraintViolation(
      final ConstraintViolationException ex) {

    final Set<String> errors = ex.getConstraintViolations().stream().map(cv -> cv.getRootBeanClass().getName()
                                                                               + " "
                                                                               + cv.getPropertyPath()
                                                                               + ": "
                                                                               + cv.getMessage()).collect(
        Collectors.toCollection(Sets::newHashSet));

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.BAD_REQUEST).setOutputMessage(
        ex.getLocalizedMessage()).setErrors(errors).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 500 exception. NullPointerException.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ NullPointerException.class }) public ResponseEntity<Object> handleUserNotFoundException(
      final Exception ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setOutputMessage(
        "Oops, Huston we have a problem.").setErrors(Sets.newHashSet("Null Pointer Exception")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 500 exception. StackOverFlow.
   *
   * @param er - error
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ StackOverflowError.class }) public ResponseEntity<Object> handleStackOverFlowError(final StackOverflowError er) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.INTERNAL_SERVER_ERROR).setOutputMessage(
        "Oops, Huston we have a problem.").setErrors(Sets.newHashSet("Stack Over Flow Error")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 405 exception. Method not allowed.
   *
   * @param ex - exception
   * @param headers - headers
   * @param status - status
   * @param request - request
   * @return {@link ResponseEntity} object
   */
  @Override protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      final HttpRequestMethodNotSupportedException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    final StringBuilder builder = new StringBuilder().append(ex.getMethod()).append(
        " method is not supported for this request. Supported methods are ");

    ex.getSupportedHttpMethods().forEach(method -> builder.append(method).append(" "));

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.METHOD_NOT_ALLOWED).setOutputMessage(
        ex.getLocalizedMessage()).setErrors(Sets.newHashSet(builder.toString())).build();

    return new ResponseEntity<>(error, headers, error.getStatus());
  }

  /**
   * Handle the 415 exception. Unsupported Media Type.
   *
   * @param ex - exception
   * @param headers - headers
   * @param status - status
   * @param request - request
   * @return {@link ResponseEntity} object
   */
  @Override protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      final HttpMediaTypeNotSupportedException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    final StringBuilder builder = new StringBuilder().append(ex.getContentType()).append(
        " media type is not supported. Supported media types are ");

    ex.getSupportedMediaTypes().forEach(type -> builder.append(type).append(" "));

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE).setOutputMessage(
        ex.getLocalizedMessage()).setErrors(Sets.newHashSet(builder.substring(0,
        builder.length() - 2))).build();

    return new ResponseEntity<>(error, headers, error.getStatus());
  }

  /**
   * A single place to customize the response body of all Exception types.
   *
   * <p>The default implementation sets the request attribute and creates a {@link ResponseEntity}
   * from the given body, headers, and status.
   *
   * @param ex the exception
   * @param body the body for the response
   * @param headers the headers for the response
   * @param status the response status
   * @param request the current request
   */
  @Override protected ResponseEntity<Object> handleExceptionInternal(
      final Exception ex,
      final Object body,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }

  /**
   * Handle the 404 exception. Address Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ AddressNotFoundException.class }) public ResponseEntity<Object> handleAddressNotFoundException(
      final AddressNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Address Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. Contacts Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ ContactsNotFoundException.class }) public ResponseEntity<Object> handleContactsNotFoundException(
      final ContactsNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Contacts Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. Personal Data Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ PersonalDataNotFoundException.class }) public ResponseEntity<Object> handlePersonalDataNotFoundException(
      final PersonalDataNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Personal Data Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. Course Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ CourseNotFoundException.class }) public ResponseEntity<Object> handleCourseNotFoundException(
      final CourseNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Course Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. Faculty Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ FacultyNotFoundException.class }) public ResponseEntity<Object> handleFacultyNotFoundException(
      final FacultyNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Faculty Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. Group Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ GroupNotFoundException.class }) public ResponseEntity<Object> handleGroupNotFoundException(
      final GroupNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Group Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. Semester Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ SemesterNotFoundException.class }) public ResponseEntity<Object> handleSemesterNotFoundException(
      final SemesterNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Semester Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. PersonRole Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ PersonRoleNotFoundException.class }) public ResponseEntity<Object> handlePersonRoleNotFoundException(
      final PersonRoleNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Person Role Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. Person Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ PersonNotFoundException.class }) public ResponseEntity<Object> handlePersonNotFoundException(
      final PersonNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Person Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. User Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ UserNotFoundException.class }) public ResponseEntity<Object> handleUserNotFoundException(
      final UserNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("User Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. Subject Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ SubjectNotFoundException.class }) public ResponseEntity<Object> handleSubjectNotFoundException(
      final SubjectNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Subject Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. Examination Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ ExaminationNotFoundException.class }) public ResponseEntity<Object> handleExaminationNotFoundException(
      final ExaminationNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Examination Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. Grade Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ GradeNotFoundException.class }) public ResponseEntity<Object> handleGradeNotFoundException(
      final GradeNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Grade Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 404 exception. ExaminationType Not Found.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ org.hibernate.exception.ConstraintViolationException.class }) public ResponseEntity<Object> handleConstraintViolationException(
      final org.hibernate.exception.ConstraintViolationException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Constraint Violation Exception")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }

  /**
   * Handle the 400 exception. Constraint violation.
   *
   * @param ex - exception
   * @return {@link ResponseEntity} object
   */
  @ExceptionHandler({ ExaminationTypeNotFoundException.class }) public ResponseEntity<Object> handleExaminationTypeNotFoundException(
      final ExaminationTypeNotFoundException ex) {

    final ErrorDetails error = new ErrorDetails.Builder().setStatus(HttpStatus.NOT_FOUND).setOutputMessage(ex.getLocalizedMessage()).setErrors(
        Sets.newHashSet("Examination Type Not Found")).build();

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }
}
