package courses.paint.mini.exception;

import courses.paint.mini.enums.ExceptionType;
import lombok.Getter;

@Getter
public class WrongFileFormatException extends BasicException {

    private final ExceptionType exceptionType;

    public WrongFileFormatException() {
        super("Wrong file format");
        exceptionType = ExceptionType.WRONG_FILE_FORMAT;
    }

}
