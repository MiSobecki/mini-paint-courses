package courses.paint.mini.usecase.course;

import courses.paint.mini.model.course.Course;
import courses.paint.mini.model.course.CourseFilters;
import courses.paint.mini.port.RequestCoursePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class GetAllCoursesFilteredUseCase {

    private final RequestCoursePort requestCoursePort;

    public Page<Course> execute(CourseFilters courseFilters,
                                Pageable pageable) {
        return requestCoursePort.getAll(courseFilters, pageable);
    }

}
