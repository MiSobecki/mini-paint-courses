package courses.paint.mini.usecase.course;

import courses.paint.mini.model.course.Course;
import courses.paint.mini.model.course.CourseFilters;
import courses.paint.mini.port.RequestCoursePort;
import lombok.RequiredArgsConstructor;

import java.awt.print.Pageable;
import java.util.Set;

@RequiredArgsConstructor
public class GetAllCoursesFilteredUseCase {

    private final RequestCoursePort requestCoursePort;

    public Set<Course> execute(CourseFilters courseFilters,
                               Pageable pageable) {
        return requestCoursePort.getAll(courseFilters, pageable);
    }

}
