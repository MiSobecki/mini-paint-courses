package courses.paint.mini.mapper.game;

import courses.paint.mini.dto.game.CourseMiniatureDto;
import courses.paint.mini.model.game.Faction;
import courses.paint.mini.model.game.Miniature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MiniatureDtoMapper {

    @Mapping(source = "factionName", target = "faction", qualifiedByName = "fromFactionName")
    Miniature toMiniature(CourseMiniatureDto courseMiniatureDto);

    @Mapping(source = "faction.name", target = "factionName")
    @Mapping(source = "faction.game.title", target = "gameTitle")
    CourseMiniatureDto fromMiniature(Miniature miniature);

    @Named("fromFactionName")
    default Faction fromFactionName(String factionName) {
        if (factionName == null) {
            return null;
        }

        var faction = new Faction();
        faction.setName(factionName);

        return faction;
    }

}
