package courses.paint.mini.repository.course;

import courses.paint.mini.entity.course.CourseStepImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseStepImageRepository extends JpaRepository<CourseStepImageEntity, String> {

    List<CourseStepImageEntity> findAllByCourseStepId(String courseStepId);

}
