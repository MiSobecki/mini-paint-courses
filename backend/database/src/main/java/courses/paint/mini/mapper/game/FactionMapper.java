package courses.paint.mini.mapper.game;

import courses.paint.mini.entity.game.FactionEntity;
import courses.paint.mini.model.game.Faction;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = GameFactionMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FactionMapper {

    @Mapping(source = "game", target = "game", qualifiedByName = "toGameWithoutFactions")
    Faction toFaction(FactionEntity factionEntity);

    @Mapping(source = "game", target = "game", qualifiedByName = "fromGameWithoutFactions")
    FactionEntity fromFaction(Faction faction);

    @Mapping(target = "miniatures", ignore = true)
    @Mapping(target = "game", ignore = true)
    Faction toFactionShortInfo(FactionEntity factionEntity);

}
