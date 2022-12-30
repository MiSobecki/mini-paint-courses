package courses.paint.mini.repository.game;

import courses.paint.mini.entity.game.FactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactionRepository extends JpaRepository<FactionEntity, String> {

    List<FactionEntity> findAllByGameId(String gameId);

}
