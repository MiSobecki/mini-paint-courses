package courses.paint.mini.port;

import courses.paint.mini.model.game.Game;

import java.util.Set;

public interface RequestGamePort {

    Set<Game> getAllShortInfo();

}
