package courses.paint.mini.mapper.game;

import courses.paint.mini.constraint.GameType;
import courses.paint.mini.entity.ProducerEntity;
import courses.paint.mini.entity.game.FactionEntity;
import courses.paint.mini.entity.game.GameEntity;
import courses.paint.mini.entity.game.MiniatureEntity;
import courses.paint.mini.mapper.ProducerMapperImpl;
import courses.paint.mini.model.Producer;
import courses.paint.mini.model.game.Faction;
import courses.paint.mini.model.game.Game;
import courses.paint.mini.model.game.Miniature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.lenient;

@RunWith(MockitoJUnitRunner.class)
public class GameMapperTest {

    @Mock
    private ProducerMapperImpl producerMapper;
    @Mock
    private GameFactionMapperImpl gameFactionMapper;

    @InjectMocks
    private GameMapperImpl gameMapper;

    private Game game;
    private GameEntity gameEntity;
    private Producer producer;
    private ProducerEntity producerEntity;

    @Before
    public void init() {
        producer = new Producer("6545", "GW");

        game = new Game("534", "LOTR", null, GameType.FANTASY, producer);
        var faction = new Faction("65423", "Orcs", null, game);

        var miniatures = Set.of(
                new Miniature("54368765", "orc big", "lord", faction)
        );
        faction.setMiniatures(miniatures);

        var factions = Set.of(
                faction
        );
        game.setFactions(factions);

        var miniatureEntitiesWithoutFaction = Set.of(
                new MiniatureEntity("54368765", "orc big", "lord", null)
        );
        var factionEntitiesWithoutGame = Set.of(
                new FactionEntity("65423", "Orcs", miniatureEntitiesWithoutFaction, null)
        );
        lenient().doReturn(factionEntitiesWithoutGame).when(gameFactionMapper).fromFactionsWithoutGame(factions);

        producerEntity = new ProducerEntity("6545", "GW");
        lenient().doReturn(producerEntity).when(producerMapper).fromProducer(producer);


        gameEntity = new GameEntity("534", "LOTR", null, GameType.FANTASY, producerEntity);
        var factionEntity = new FactionEntity("65423", "Orcs", null, gameEntity);

        var miniatureEntities = Set.of(
                new MiniatureEntity("54368765", "orc big", "lord", factionEntity)
        );
        factionEntity.setMiniatures(miniatureEntities);

        var factionEntities = Set.of(
                factionEntity
        );
        gameEntity.setFactions(factionEntities);

        var miniaturesWithoutFaction = Set.of(
                new Miniature("54368765", "orc big", "lord", null)
        );
        var factionsWithoutGame = Set.of(
                new Faction("65423", "Orcs", miniaturesWithoutFaction, null)
        );
        lenient().doReturn(factionsWithoutGame).when(gameFactionMapper).toFactionsWithoutGame(factionEntities);

        lenient().doReturn(producer).when(producerMapper).toProducer(producerEntity);
    }

    @Test
    public void shouldMapGameToGameEntityWithFactionsCorrectly() {
        // when
        var result = gameMapper.fromGame(game);

        // then
        assertEquals(game.getTitle(), result.getTitle());
        assertEquals(game.getType(), result.getType());
        assertEquals(game.getId(), result.getId());
        assertEquals(producerEntity, result.getProducer());

        assertEquals(game.getFactions().stream()
                        .map(Faction::getId)
                        .collect(Collectors.toSet()),
                result.getFactions().stream()
                        .map(FactionEntity::getId)
                        .collect(Collectors.toSet()));
        assertEquals(game.getFactions().stream()
                        .map(Faction::getName)
                        .collect(Collectors.toSet()),
                result.getFactions().stream()
                        .map(FactionEntity::getName)
                        .collect(Collectors.toSet()));

        assertEquals(game.getFactions().stream()
                        .map(Faction::getMiniatures)
                        .flatMap(Collection::stream)
                        .map(Miniature::getId)
                        .collect(Collectors.toSet()),
                result.getFactions().stream()
                        .map(FactionEntity::getMiniatures)
                        .flatMap(Collection::stream)
                        .map(MiniatureEntity::getId)
                        .collect(Collectors.toSet()));

        assertTrue(result.getFactions().stream()
                .allMatch(x -> x.getGame() == null));

        assertTrue(result.getFactions().stream()
                .map(FactionEntity::getMiniatures)
                .flatMap(Collection::stream)
                .allMatch(x -> x.getFaction() == null));
    }

    @Test
    public void shouldMapGameEntityToGameWithFactionsCorrectly() {
        // when
        var result = gameMapper.toGame(gameEntity);

        // then
        assertEquals(gameEntity.getTitle(), result.getTitle());
        assertEquals(gameEntity.getType(), result.getType());
        assertEquals(gameEntity.getId(), result.getId());
        assertEquals(producer, result.getProducer());

        assertEquals(gameEntity.getFactions().stream()
                        .map(FactionEntity::getId)
                        .collect(Collectors.toSet()),
                result.getFactions().stream()
                        .map(Faction::getId)
                        .collect(Collectors.toSet()));
        assertEquals(gameEntity.getFactions().stream()
                        .map(FactionEntity::getName)
                        .collect(Collectors.toSet()),
                result.getFactions().stream()
                        .map(Faction::getName)
                        .collect(Collectors.toSet()));

        assertEquals(gameEntity.getFactions().stream()
                        .map(FactionEntity::getMiniatures)
                        .flatMap(Collection::stream)
                        .map(MiniatureEntity::getId)
                        .collect(Collectors.toSet()),
                result.getFactions().stream()
                        .map(Faction::getMiniatures)
                        .flatMap(Collection::stream)
                        .map(Miniature::getId)
                        .collect(Collectors.toSet()));

        assertTrue(result.getFactions().stream()
                .allMatch(x -> x.getGame() == null));

        assertTrue(result.getFactions().stream()
                .map(Faction::getMiniatures)
                .flatMap(Collection::stream)
                .allMatch(x -> x.getFaction() == null));
    }

}