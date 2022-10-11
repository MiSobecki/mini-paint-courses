package courses.paint.mini.repository.product;

import courses.paint.mini.entity.product.PaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintRepository extends JpaRepository<PaintEntity, String> {
}
