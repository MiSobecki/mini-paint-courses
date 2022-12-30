package courses.paint.mini.usecase.game;

import courses.paint.mini.model.game.Miniature;
import courses.paint.mini.port.RequestMiniaturePort;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class GetAllMiniaturesShortInfoByFactionUseCase {

    private final RequestMiniaturePort requestMiniaturePort;

    public Set<Miniature> execute(String factionId) {
        return requestMiniaturePort.getAllShortInfoByFactionId(factionId);
    }

}
