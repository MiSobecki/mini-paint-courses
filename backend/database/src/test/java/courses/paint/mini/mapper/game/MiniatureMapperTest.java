package courses.paint.mini.mapper.game;

import courses.paint.mini.constraint.GameType;
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

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.lenient;

@RunWith(MockitoJUnitRunner.class)
public class MiniatureMapperTest {

    @Mock
    private FactionMiniatureMapperImpl factionMiniatureMapper;

    @InjectMocks
    private MiniatureMapperImpl miniatureMapper;

    private Miniature miniature;
    private MiniatureEntity miniatureEntity;
    private Game game;
    private GameEntity gameEntity;

    @Before
    public void init() {
        miniature = new Miniature("123", "mini", "ranged", null);

        var producer = new Producer("4352", "GW");
        game = new Game("432", "new Game", null, GameType.FANTASY, producer);

        var faction = new Faction("245", "goodFaction", Set.of(miniature), game);
        miniature.setFaction(faction);

        var producerEntity = new ProducerEntity("4352", "GW");
        gameEntity = new GameEntity("432", "new Game", null, GameType.FANTASY, producerEntity);

        var factionEntityWithoutMiniatures = new FactionEntity("245", "goodFaction", null, gameEntity);
        lenient().doReturn(factionEntityWithoutMiniatures).when(factionMiniatureMapper).fromFactionWithoutMiniatures(faction);

        miniatureEntity = new MiniatureEntity("123", "mini", "ranged", null);
        var factionEntity = new FactionEntity("245", "goodFaction", Set.of(miniatureEntity), gameEntity);
        miniatureEntity.setFaction(factionEntity);

        var factionWithoutMiniatures = new Faction("245", "goodFaction", null, game);
        lenient().doReturn(factionWithoutMiniatures).when(factionMiniatureMapper).toFactionWithoutMiniatures(factionEntity);
    }

    @Test
    public void shouldMapMiniatureToMiniatureEntityWithFactionCorrectly() {
        // when
        var result = miniatureMapper.fromMiniature(miniature);

        // then
        assertEquals(miniature.getId(), result.getId());
        assertEquals(miniature.getName(), result.getName());
        assertEquals(miniature.getType(), result.getType());
        assertEquals(miniature.getFaction().getId(), result.getFaction().getId());
        assertEquals(miniature.getFaction().getName(), result.getFaction().getName());
        assertEquals(gameEntity, result.getFaction().getGame());

        assertNull(result.getFaction().getMiniatures());
    }

    @Test
    public void shouldMapMiniatureEntityToMiniatureWithFactionCorrectly() {
        // when
        var result = miniatureMapper.toMiniature(miniatureEntity);

        // then
        assertEquals(miniatureEntity.getId(), result.getId());
        assertEquals(miniatureEntity.getName(), result.getName());
        assertEquals(miniatureEntity.getType(), result.getType());
        assertEquals(miniatureEntity.getFaction().getId(), result.getFaction().getId());
        assertEquals(miniatureEntity.getFaction().getName(), result.getFaction().getName());
        assertEquals(game, result.getFaction().getGame());

        assertNull(result.getFaction().getMiniatures());
    }

}