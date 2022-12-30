package courses.paint.mini.repository.game;

import courses.paint.mini.entity.game.MiniatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiniatureRepository extends JpaRepository<MiniatureEntity, String> {

    List<MiniatureEntity> findAllByFactionId(String factionId);

}
