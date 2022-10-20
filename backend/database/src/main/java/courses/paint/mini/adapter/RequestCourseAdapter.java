package courses.paint.mini.adapter;

import courses.paint.mini.mapper.course.CourseMapper;
import courses.paint.mini.model.CourseSpecification;
import courses.paint.mini.model.course.Course;
import courses.paint.mini.model.course.CourseFilters;
import courses.paint.mini.port.RequestCoursePort;
import courses.paint.mini.repository.course.CourseRepository;
import courses.paint.mini.repository.product.PaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestCourseAdapter implements RequestCoursePort {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final PaintRepository paintRepository;

    @Override
    public Optional<Course> getById(String id) {
        var courseEntity = courseRepository.findById(id);

        return courseEntity.map(courseMapper::toCourse);
    }

    @Override
    public Optional<Course> getByTitleAndUserId(String title, String userId) {
        var courseEntity = courseRepository.findByTitleAndUserId(title, userId);

        return courseEntity.map(courseMapper::toCourse);
    }

    @Override
    public Page<Course> getAll(CourseFilters courseFilters, Pageable pageable) {
        return courseRepository.findAll(new CourseSpecification(courseFilters), pageable)
                .map(courseMapper::toCourse);
    }

}
