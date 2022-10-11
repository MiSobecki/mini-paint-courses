package courses.paint.mini.port;

import courses.paint.mini.model.course.Course;
import courses.paint.mini.model.course.CourseFilters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RequestCoursePort {

    Optional<Course> getById(String id);

    Optional<Course> getByTitleAndUserId(String title,
                                         String userId);

    Page<Course> getAll(CourseFilters courseFilters,
                        Pageable pageable);

}
