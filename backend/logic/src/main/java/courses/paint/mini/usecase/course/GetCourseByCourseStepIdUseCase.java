package courses.paint.mini.usecase.course;

import courses.paint.mini.exception.course.NotFoundCourseException;
import courses.paint.mini.model.course.Course;
import courses.paint.mini.port.RequestCoursePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetCourseByCourseStepIdUseCase {

    private final RequestCoursePort requestCoursePort;

    public Course execute(String stepId) {
        return requestCoursePort.getByCourseStepId(stepId).orElseThrow(() ->
                new NotFoundCourseException("Course with step of id: '" + stepId + "' does not exist"));
    }

}
