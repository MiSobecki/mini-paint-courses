package courses.paint.mini.exception.course;

import courses.paint.mini.enums.ExceptionType;
import courses.paint.mini.exception.BasicException;
import lombok.Getter;

@Getter
public class NotFoundCourseException extends BasicException {

    private final ExceptionType exceptionType;

    public NotFoundCourseException(String courseId) {
        super("Course of id: '" + courseId + "' does not exist");
        exceptionType = ExceptionType.NOT_FOUND;
    }
}
