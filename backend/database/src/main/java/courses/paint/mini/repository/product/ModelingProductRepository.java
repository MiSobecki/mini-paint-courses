package courses.paint.mini.repository.product;

import courses.paint.mini.entity.product.ModelingProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelingProductRepository extends JpaRepository<ModelingProductEntity, String> {
}
