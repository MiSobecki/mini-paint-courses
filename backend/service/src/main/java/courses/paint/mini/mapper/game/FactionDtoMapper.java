package courses.paint.mini.mapper.game;

import courses.paint.mini.dto.game.FactionShortInfoDto;
import courses.paint.mini.model.game.Faction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactionDtoMapper {

    FactionShortInfoDto toFactionShortInfoDto(Faction faction);

}
