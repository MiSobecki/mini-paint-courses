package courses.paint.mini.exception;

import courses.paint.mini.enums.ExceptionType;
import lombok.Getter;

@Getter
public class ForbiddenCourseActionException extends BasicException {

    private final ExceptionType exceptionType;

    public ForbiddenCourseActionException(String errorMessage) {
        super("Access denied" + (errorMessage.isEmpty() ? "" : (": " + errorMessage)));
        exceptionType = ExceptionType.ACCESS_DENIED;
    }
}
