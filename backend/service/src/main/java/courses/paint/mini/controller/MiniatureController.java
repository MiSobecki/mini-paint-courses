package courses.paint.mini.controller;

import courses.paint.mini.dto.game.MiniatureShortInfoDto;
import courses.paint.mini.mapper.game.MiniatureDtoMapper;
import courses.paint.mini.usecase.game.GetAllMiniaturesShortInfoByFactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MiniatureController {

    private final GetAllMiniaturesShortInfoByFactionUseCase getAllMiniaturesShortInfoByFactionUseCase;
    private final MiniatureDtoMapper miniatureDtoMapper;

    @GetMapping("/factions/{factionId}/miniatures")
    public List<MiniatureShortInfoDto> getAllByFaction(@PathVariable String factionId) {
        return getAllMiniaturesShortInfoByFactionUseCase.execute(factionId).stream()
                .map(miniatureDtoMapper::toMiniatureShortInfoDto)
                .toList();
    }

}
