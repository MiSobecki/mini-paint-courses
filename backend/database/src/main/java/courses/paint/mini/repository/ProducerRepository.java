package courses.paint.mini.repository;

import courses.paint.mini.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<ProducerEntity, String> {
}
