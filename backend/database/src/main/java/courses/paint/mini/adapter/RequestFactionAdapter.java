package courses.paint.mini.adapter;

import courses.paint.mini.mapper.game.FactionMapper;
import courses.paint.mini.model.game.Faction;
import courses.paint.mini.port.RequestFactionPort;
import courses.paint.mini.repository.game.FactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestFactionAdapter implements RequestFactionPort {

    private final FactionRepository factionRepository;
    private final FactionMapper factionMapper;

    @Override
    public Set<Faction> getAllShortInfoByGameId(String gameId) {
        return factionRepository.findAllByGameId(gameId).stream()
                .map(factionMapper::toFactionShortInfo)
                .collect(Collectors.toSet());
    }

}
