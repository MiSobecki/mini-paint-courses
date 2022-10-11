package courses.paint.mini.mapper.game;

import courses.paint.mini.entity.game.FactionEntity;
import courses.paint.mini.entity.game.GameEntity;
import courses.paint.mini.entity.game.MiniatureEntity;
import courses.paint.mini.mapper.ProducerMapper;
import courses.paint.mini.model.game.Faction;
import courses.paint.mini.model.game.Game;
import courses.paint.mini.model.game.Miniature;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring", uses = ProducerMapper.class)
public interface GameFactionMapper {

    @Mapping(target = "factions", ignore = true)
    @Named("toGameWithoutFactions")
    Game toGameWithoutFactions(GameEntity gameEntity);

    @Mapping(target = "factions", ignore = true)
    @Named("fromGameWithoutFactions")
    GameEntity fromGameWithoutFactions(Game game);


    @Mapping(target = "game", ignore = true)
    @Mapping(source = "miniatures", target = "miniatures", qualifiedByName = "toMiniatureWithoutFaction")
    @Named("toFactionWithoutGame")
    Faction toFactionWithoutGame(FactionEntity factionEntity);

    @Mapping(target = "game", ignore = true)
    @Mapping(source = "miniatures", target = "miniatures", qualifiedByName = "fromMiniatureWithoutFaction")
    @Named("fromFactionWithoutGame")
    FactionEntity fromFactionWithoutGame(Faction faction);

    @IterableMapping(qualifiedByName = "toFactionWithoutGame")
    Set<Faction> toFactionsWithoutGame(Set<FactionEntity> factionEntities);

    @IterableMapping(qualifiedByName = "fromFactionWithoutGame")
    Set<FactionEntity> fromFactionsWithoutGame(Set<Faction> factions);


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

}
