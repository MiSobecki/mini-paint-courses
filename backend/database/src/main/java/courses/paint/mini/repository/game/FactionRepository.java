package courses.paint.mini.repository.game;

import courses.paint.mini.entity.game.FactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactionRepository extends JpaRepository<FactionEntity, String> {
}
