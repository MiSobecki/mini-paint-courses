package courses.paint.mini.exception;

import courses.paint.mini.enums.ExceptionType;
import lombok.Getter;

@Getter
public class BasicException extends RuntimeException {

    private final ExceptionType exceptionType;

    public BasicException(String errorMessage) {
        super(errorMessage);
        this.exceptionType = ExceptionType.UNKNOWN;
    }

}
