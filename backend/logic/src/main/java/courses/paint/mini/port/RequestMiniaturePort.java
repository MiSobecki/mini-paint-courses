package courses.paint.mini.port;

import courses.paint.mini.model.game.Miniature;

import java.util.Set;

public interface RequestMiniaturePort {

    Set<Miniature> getAllShortInfoByFactionId(String factionId);

}
