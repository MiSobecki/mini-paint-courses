package courses.paint.mini.config;

import courses.paint.mini.port.RequestFactionPort;
import courses.paint.mini.port.RequestGamePort;
import courses.paint.mini.port.RequestMiniaturePort;
import courses.paint.mini.usecase.game.GetAllFactionsShortInfoByGameUseCase;
import courses.paint.mini.usecase.game.GetAllGamesShortInfoUseCase;
import courses.paint.mini.usecase.game.GetAllMiniaturesShortInfoByFactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GameConfig {

    private final RequestGamePort requestGamePort;
    private final RequestFactionPort requestFactionPort;
    private final RequestMiniaturePort requestMiniaturePort;

    @Bean
    public GetAllGamesShortInfoUseCase getAllGamesShortInfoUseCase() {
        return new GetAllGamesShortInfoUseCase(requestGamePort);
    }

    @Bean
    public GetAllFactionsShortInfoByGameUseCase getAllFactionsShortInfoByGameUseCase() {
        return new GetAllFactionsShortInfoByGameUseCase(requestFactionPort);
    }

    @Bean
    public GetAllMiniaturesShortInfoByFactionUseCase getAllMiniaturesShortInfoByFactionUseCase() {
        return new GetAllMiniaturesShortInfoByFactionUseCase(requestMiniaturePort);
    }

}
