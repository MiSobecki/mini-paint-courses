package courses.paint.mini.repository.game;

import courses.paint.mini.entity.game.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, String> {
}
