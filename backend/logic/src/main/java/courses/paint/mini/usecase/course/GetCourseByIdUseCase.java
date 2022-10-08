package courses.paint.mini.usecase.course;

import courses.paint.mini.exception.course.NonExistingCourseException;
import courses.paint.mini.model.course.Course;
import courses.paint.mini.port.RequestCoursePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetCourseByIdUseCase {

    private final RequestCoursePort requestCoursePort;

    public Course execute(String id) {
        return requestCoursePort.getById(id).orElseThrow(() -> new NonExistingCourseException(id));
    }

}
