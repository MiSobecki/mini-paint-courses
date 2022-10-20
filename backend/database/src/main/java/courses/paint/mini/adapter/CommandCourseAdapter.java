package courses.paint.mini.adapter;

import courses.paint.mini.entity.course.CourseStepEntity;
import courses.paint.mini.mapper.course.CourseMapper;
import courses.paint.mini.model.course.Course;
import courses.paint.mini.model.course.CourseStep;
import courses.paint.mini.port.CommandCoursePort;
import courses.paint.mini.repository.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandCourseAdapter implements CommandCoursePort {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        var courseEntity = courseMapper.fromCourse(course);

        for (CourseStepEntity courseStep : courseEntity.getSteps()) {
            courseStep.setCourse(courseEntity);
        }

        courseEntity = courseRepository.save(courseEntity);

        return courseMapper.toCourse(courseEntity);
    }

    @Override
    public Course update(Course course) {
        var courseEntity = courseMapper.fromCourse(course);

        for (CourseStepEntity courseStep : courseEntity.getSteps()) {
            courseStep.setCourse(courseEntity);
        }

        courseEntity = courseRepository.save(courseEntity);

        return courseMapper.toCourse(courseEntity);
    }

    @Override
    public void delete(String courseId) {
        courseRepository.deleteById(courseId);
    }
}
