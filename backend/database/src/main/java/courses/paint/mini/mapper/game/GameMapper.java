package courses.paint.mini.mapper.game;

import courses.paint.mini.entity.game.GameEntity;
import courses.paint.mini.mapper.ProducerMapper;
import courses.paint.mini.model.game.Game;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {ProducerMapper.class,
                GameFactionMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GameMapper {

    Game toGame(GameEntity gameEntity);

    GameEntity fromGame(Game game);

}
