package courses.paint.mini.adapter;

import courses.paint.mini.mapper.game.MiniatureMapper;
import courses.paint.mini.model.game.Miniature;
import courses.paint.mini.port.RequestMiniaturePort;
import courses.paint.mini.repository.game.MiniatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestMiniatureAdapter implements RequestMiniaturePort {

    private final MiniatureRepository miniatureRepository;
    private final MiniatureMapper miniatureMapper;

    @Override
    public Set<Miniature> getAllShortInfoByFactionId(String factionId) {
        return miniatureRepository.findAllByFactionId(factionId).stream()
                .map(miniatureMapper::toMiniatureShortInfo)
                .collect(Collectors.toSet());
    }

}
