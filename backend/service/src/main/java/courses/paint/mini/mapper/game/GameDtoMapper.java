package courses.paint.mini.mapper.game;

import courses.paint.mini.dto.game.GameShortInfoDto;
import courses.paint.mini.model.game.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameDtoMapper {

    GameShortInfoDto toGameShortInfoDto(Game game);

}
