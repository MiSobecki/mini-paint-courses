package courses.paint.mini.port;

import courses.paint.mini.model.course.Course;
import courses.paint.mini.model.course.CourseFilters;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.Set;

public interface RequestCoursePort {

    Optional<Course> getById(String id);

    Optional<Course> getByTitleAndUserId(String title,
                                         String userId);

    Set<Course> getAll(CourseFilters courseFilters,
                       Pageable pageable);

}
