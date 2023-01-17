package courses.paint.mini.repository.course;

import courses.paint.mini.entity.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, String>, JpaSpecificationExecutor<CourseEntity> {

    Optional<CourseEntity> findByTitleAndUserId(String title,
                                                String userId);

    Optional<CourseEntity> findByStepsId(String courseStepId);

}
