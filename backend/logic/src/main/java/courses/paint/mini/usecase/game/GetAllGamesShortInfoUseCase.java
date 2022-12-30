package courses.paint.mini.usecase.game;

import courses.paint.mini.model.game.Game;
import courses.paint.mini.port.RequestGamePort;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class GetAllGamesShortInfoUseCase {

    private final RequestGamePort requestGamePort;

    public Set<Game> execute() {
        return requestGamePort.getAllShortInfo();
    }

}
