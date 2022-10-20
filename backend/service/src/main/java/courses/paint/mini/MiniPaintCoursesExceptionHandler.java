package courses.paint.mini;

import courses.paint.mini.dto.ErrorMessageResponse;
import courses.paint.mini.enums.ExceptionType;
import courses.paint.mini.exception.BasicException;
import courses.paint.mini.exception.course.NonExistingCourseException;
import courses.paint.mini.exception.course.NonUniqueCourseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MiniPaintCoursesExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BasicException.class})
    protected ResponseEntity<ErrorMessageResponse> handleException(Exception ex) {
        return new ResponseEntity<>(
                new ErrorMessageResponse(
                        ex.getMessage(),
                        ExceptionType.UNKNOWN,
                        LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NonExistingCourseException.class)
    protected ResponseEntity<ErrorMessageResponse> handleNonExistingException(BasicException ex) {
        return new ResponseEntity<>(
                new ErrorMessageResponse(
                        ex.getMessage(),
                        ex.getExceptionType(),
                        LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NonUniqueCourseException.class)
    protected ResponseEntity<ErrorMessageResponse> handleNonUniqueException(BasicException ex) {
        return new ResponseEntity<>(
                new ErrorMessageResponse(
                        ex.getMessage(),
                        ex.getExceptionType(),
                        LocalDateTime.now()),
                HttpStatus.CONFLICT);
    }

}
