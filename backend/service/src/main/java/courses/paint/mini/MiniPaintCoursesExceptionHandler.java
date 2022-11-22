package courses.paint.mini;

import courses.paint.mini.dto.errorresponse.ErrorMessageResponse;
import courses.paint.mini.dto.errorresponse.ValidationErrorResponse;
import courses.paint.mini.enums.ExceptionType;
import courses.paint.mini.exception.BasicException;
import courses.paint.mini.exception.course.NonUniqueCourseException;
import courses.paint.mini.exception.course.NotFoundCourseException;
import courses.paint.mini.exception.user.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MiniPaintCoursesExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BasicException.class, Exception.class})
    protected ResponseEntity<ErrorMessageResponse> handleException(Exception ex) {
        return new ResponseEntity<>(
                new ErrorMessageResponse(
                        ex.getMessage(),
                        ExceptionType.UNKNOWN,
                        LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {
            NotFoundCourseException.class,
            NotFoundUserException.class,
            NotFoundRoleException.class,
            NotFoundRoleByIdException.class})
    protected ResponseEntity<ErrorMessageResponse> handleNonExistingException(BasicException ex) {
        return new ResponseEntity<>(
                new ErrorMessageResponse(
                        ex.getMessage(),
                        ex.getExceptionType(),
                        LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NonUniqueCourseException.class, NonUniqueRoleException.class, NonUniqueUserException.class})
    protected ResponseEntity<ErrorMessageResponse> handleNonUniqueException(BasicException ex) {
        return new ResponseEntity<>(
                new ErrorMessageResponse(
                        ex.getMessage(),
                        ex.getExceptionType(),
                        LocalDateTime.now()),
                HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(
                new ValidationErrorResponse(
                        errors,
                        ExceptionType.INVALID,
                        LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

}
