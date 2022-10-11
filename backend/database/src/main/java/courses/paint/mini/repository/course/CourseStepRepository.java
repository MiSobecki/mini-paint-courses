package courses.paint.mini.repository.course;

import courses.paint.mini.entity.course.CourseStepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseStepRepository extends JpaRepository<CourseStepEntity, String> {
}
