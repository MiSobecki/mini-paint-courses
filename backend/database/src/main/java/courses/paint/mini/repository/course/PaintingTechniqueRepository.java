package courses.paint.mini.repository.course;

import courses.paint.mini.entity.course.PaintingTechniqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintingTechniqueRepository extends JpaRepository<PaintingTechniqueEntity, String> {
}
