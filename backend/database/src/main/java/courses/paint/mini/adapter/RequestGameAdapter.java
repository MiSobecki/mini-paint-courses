package courses.paint.mini.adapter;

import courses.paint.mini.mapper.game.GameMapper;
import courses.paint.mini.model.game.Game;
import courses.paint.mini.port.RequestGamePort;
import courses.paint.mini.repository.game.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestGameAdapter implements RequestGamePort {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    @Override
    public Set<Game> getAllShortInfo() {
        return gameRepository.findAll().stream()
                .map(gameMapper::toGameShortInfo)
                .collect(Collectors.toSet());
    }

}
