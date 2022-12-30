package courses.paint.mini.controller;

import courses.paint.mini.dto.game.FactionShortInfoDto;
import courses.paint.mini.mapper.game.FactionDtoMapper;
import courses.paint.mini.usecase.game.GetAllFactionsShortInfoByGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FactionController {

    private final GetAllFactionsShortInfoByGameUseCase getAllFactionsShortInfoByGameUseCase;
    private final FactionDtoMapper factionDtoMapper;

    @GetMapping("/games/{gameId}/factions")
    public List<FactionShortInfoDto> getAllByGame(@PathVariable String gameId) {
        return getAllFactionsShortInfoByGameUseCase.execute(gameId).stream()
                .map(factionDtoMapper::toFactionShortInfoDto)
                .toList();
    }

}
