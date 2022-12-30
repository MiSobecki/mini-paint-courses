package courses.paint.mini.usecase.game;

import courses.paint.mini.model.game.Faction;
import courses.paint.mini.port.RequestFactionPort;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class GetAllFactionsShortInfoByGameUseCase {

    private final RequestFactionPort requestFactionPort;

    public Set<Faction> execute(String gameId) {
        return requestFactionPort.getAllShortInfoByGameId(gameId);
    }

}
