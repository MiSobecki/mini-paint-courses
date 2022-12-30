package courses.paint.mini.controller;

import courses.paint.mini.dto.game.GameShortInfoDto;
import courses.paint.mini.mapper.game.GameDtoMapper;
import courses.paint.mini.usecase.game.GetAllGamesShortInfoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GetAllGamesShortInfoUseCase getAllGamesShortInfoUseCase;
    private final GameDtoMapper gameDtoMapper;

    @GetMapping
    public List<GameShortInfoDto> getAll() {
        return getAllGamesShortInfoUseCase.execute().stream()
                .map(gameDtoMapper::toGameShortInfoDto)
                .toList();
    }

}
