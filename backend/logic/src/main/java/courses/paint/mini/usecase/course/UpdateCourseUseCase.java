package courses.paint.mini.usecase.course;

import courses.paint.mini.exception.course.NonExistingCourseException;
import courses.paint.mini.model.course.Course;
import courses.paint.mini.port.CommandCoursePort;
import courses.paint.mini.port.RequestCoursePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateCourseUseCase {

    private final GetCourseByIdUseCase getCourseByIdUseCase;
    private final CommandCoursePort commandCoursePort;

    public Course execute(Course course,
                          String id) {
        var existingCourse = getCourseByIdUseCase.execute(id);

        existingCourse.setTitle(course.getTitle());
        existingCourse.setSteps(course.getSteps());

        return commandCoursePort.update(existingCourse);
    }

}
