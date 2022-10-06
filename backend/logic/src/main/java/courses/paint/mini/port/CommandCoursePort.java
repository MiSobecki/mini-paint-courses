package courses.paint.mini.port;

import courses.paint.mini.model.course.Course;

public interface CommandCoursePort {

    Course create(Course course);

    Course update(Course course);

    void delete(String courseId);

}
