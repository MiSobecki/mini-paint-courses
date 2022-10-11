package courses.paint.mini.mapper.game;

import courses.paint.mini.entity.game.FactionEntity;
import courses.paint.mini.entity.game.GameEntity;
import courses.paint.mini.entity.game.MiniatureEntity;
import courses.paint.mini.model.game.Faction;
import courses.paint.mini.model.game.Game;
import courses.paint.mini.model.game.Miniature;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface FactionMiniatureMapper {

    @Mapping(target = "miniatures", ignore = true)
    @Mapping(source = "game", target = "game", qualifiedByName = "toGameWithoutFactions")
    @Named("toFactionWithoutMiniatures")
    Faction toFactionWithoutMiniatures(FactionEntity factionEntity);

    @Mapping(target = "miniatures", ignore = true)
    @Mapping(source = "game", target = "game", qualifiedByName = "fromGameWithoutFactions")
    @Named("fromFactionWithoutMiniatures")
    FactionEntity fromFactionWithoutMiniatures(Faction faction);


    @Mapping(target = "faction", ignore = true)
    @Named("toMiniatureWithoutFaction")
    Miniature toMiniatureWithoutFaction(MiniatureEntity miniatureEntity);

    @Mapping(target = "faction", ignore = true)
    @Named("fromMiniatureWithoutFaction")
    MiniatureEntity fromMiniatureWithoutFaction(Miniature miniature);

    @IterableMapping(qualifiedByName = "toMiniatureWithoutFaction")
    Set<Miniature> toMiniaturesWithoutFaction(Set<MiniatureEntity> miniatureEntities);

    @IterableMapping(qualifiedByName = "fromMiniatureWithoutFaction")
    Set<MiniatureEntity> fromMiniaturesWithoutFaction(Set<Miniature> miniatures);


    @Mapping(target = "factions", ignore = true)
    @Named("toGameWithoutFactions")
    Game toGameWithoutFactions(GameEntity gameEntity);

    @Mapping(target = "factions", ignore = true)
    @Named("fromGameWithoutFactions")
    GameEntity fromGameWithoutFactions(Game game);

}
