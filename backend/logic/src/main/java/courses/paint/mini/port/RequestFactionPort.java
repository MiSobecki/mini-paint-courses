package courses.paint.mini.port;

import courses.paint.mini.model.game.Faction;

import java.util.Set;

public interface RequestFactionPort {

    Set<Faction> getAllShortInfoByGameId(String gameId);

}
