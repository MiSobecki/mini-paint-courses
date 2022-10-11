package courses.paint.mini.adapter;

import courses.paint.mini.mapper.course.CourseMapper;
import courses.paint.mini.model.course.Course;
import courses.paint.mini.port.CommandCoursePort;
import courses.paint.mini.repository.course.CourseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandCourseAdapter implements CommandCoursePort {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        var courseEntity = courseMapper.fromCourse(course);
        courseEntity = courseRepository.save(courseEntity);

        return courseMapper.toCourse(courseEntity);
    }

    @Override
    public Course update(Course course) {
        var courseEntity = courseMapper.fromCourse(course);
        courseEntity = courseRepository.save(courseEntity);

        return courseMapper.toCourse(courseEntity);
    }

    @Override
    public void delete(String courseId) {
        courseRepository.deleteById(courseId);
    }
}
