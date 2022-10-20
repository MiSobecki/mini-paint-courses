package courses.paint.mini.mapper.game;

import courses.paint.mini.enums.GameType;
import courses.paint.mini.entity.ProducerEntity;
import courses.paint.mini.entity.game.FactionEntity;
import courses.paint.mini.entity.game.GameEntity;
import courses.paint.mini.entity.game.MiniatureEntity;
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

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.lenient;

@RunWith(MockitoJUnitRunner.class)
public class FactionMapperTest {

    @Mock
    private GameFactionMapperImpl gameFactionMapper;

    @InjectMocks
    private FactionMapperImpl factionMapper;

    private Faction faction;
    private FactionEntity factionEntity;
    private Producer producer;
    private ProducerEntity producerEntity;

    @Before
    public void init() {
        producer = new Producer("547653", "GW");
        var game = new Game("534", "LOTR", null, GameType.FANTASY, producer);
        faction = new Faction("1234", "Orcs", null, game);
        game.setFactions(Set.of(faction));
        var miniatures = Set.of(
                new Miniature("5325", "lord Adrecus", "lord", faction)
        );
        faction.setMiniatures(miniatures);

        producerEntity = new ProducerEntity("547653", "GW");
        var gameEntityWithoutFactions = new GameEntity("534", "LOTR", null, GameType.FANTASY, producerEntity);
        lenient().doReturn(gameEntityWithoutFactions).when(gameFactionMapper).fromGameWithoutFactions(game);

        var miniatureEntitiesWithoutFaction = Set.of(
                new MiniatureEntity("5325", "lord Adrecus", "lord", null)
        );
        lenient().doReturn(miniatureEntitiesWithoutFaction).when(gameFactionMapper).fromMiniaturesWithoutFaction(miniatures);


        var gameEntity = new GameEntity("534", "LOTR", null, GameType.FANTASY, producerEntity);
        factionEntity = new FactionEntity("1234", "Orcs", null, gameEntity);
        gameEntity.setFactions(Set.of(factionEntity));
        var miniatureEntities = Set.of(
                new MiniatureEntity("5325", "lord Adrecus", "lord", factionEntity)
        );
        factionEntity.setMiniatures(miniatureEntities);

        var gameWithoutFactions = new Game("534", "LOTR", null, GameType.FANTASY, producer);
        lenient().doReturn(gameWithoutFactions).when(gameFactionMapper).toGameWithoutFactions(gameEntity);

        var miniaturesWithoutFaction = Set.of(
                new Miniature("5325", "lord Adrecus", "lord", null)
        );
        lenient().doReturn(miniaturesWithoutFaction).when(gameFactionMapper).toMiniaturesWithoutFaction(miniatureEntities);
    }

    @Test
    public void shouldMapFactionToFactionEntityWithGameAndMiniaturesCorrectly() {
        // when
        var result = factionMapper.fromFaction(faction);

        // then
        assertEquals(faction.getName(), result.getName());
        assertEquals(faction.getId(), result.getId());

        assertEquals(faction.getGame().getId(), result.getGame().getId());
        assertEquals(faction.getGame().getType(), result.getGame().getType());
        assertEquals(faction.getGame().getTitle(), result.getGame().getTitle());
        assertEquals(producerEntity, result.getGame().getProducer());
        assertNull(result.getGame().getFactions());

        assertEquals(faction.getMiniatures().stream()
                        .map(Miniature::getName)
                        .collect(Collectors.toSet()),
                result.getMiniatures().stream()
                        .map(MiniatureEntity::getName)
                        .collect(Collectors.toSet()));
        assertEquals(faction.getMiniatures().stream()
                        .map(Miniature::getType)
                        .collect(Collectors.toSet()),
                result.getMiniatures().stream()
                        .map(MiniatureEntity::getType)
                        .collect(Collectors.toSet()));
        assertEquals(faction.getMiniatures().stream()
                        .map(Miniature::getId)
                        .collect(Collectors.toSet()),
                result.getMiniatures().stream()
                        .map(MiniatureEntity::getId)
                        .collect(Collectors.toSet()));
        assertTrue(result.getMiniatures().stream()
                .map(MiniatureEntity::getFaction)
                .allMatch(Objects::isNull));
    }

    @Test
    public void shouldMapFactionEntityToFactionWithGameAndMiniaturesCorrectly() {
        // when
        var result = factionMapper.toFaction(factionEntity);

        // then
        assertEquals(factionEntity.getName(), result.getName());
        assertEquals(factionEntity.getId(), result.getId());

        assertEquals(factionEntity.getGame().getId(), result.getGame().getId());
        assertEquals(factionEntity.getGame().getType(), result.getGame().getType());
        assertEquals(factionEntity.getGame().getTitle(), result.getGame().getTitle());
        assertEquals(producer, result.getGame().getProducer());
        assertNull(result.getGame().getFactions());

        assertEquals(factionEntity.getMiniatures().stream()
                        .map(MiniatureEntity::getName)
                        .collect(Collectors.toSet()),
                result.getMiniatures().stream()
                        .map(Miniature::getName)
                        .collect(Collectors.toSet()));
        assertEquals(factionEntity.getMiniatures().stream()
                        .map(MiniatureEntity::getType)
                        .collect(Collectors.toSet()),
                result.getMiniatures().stream()
                        .map(Miniature::getType)
                        .collect(Collectors.toSet()));
        assertEquals(factionEntity.getMiniatures().stream()
                        .map(MiniatureEntity::getId)
                        .collect(Collectors.toSet()),
                result.getMiniatures().stream()
                        .map(Miniature::getId)
                        .collect(Collectors.toSet()));
        assertTrue(result.getMiniatures().stream()
                .map(Miniature::getFaction)
                .allMatch(Objects::isNull));
    }

}