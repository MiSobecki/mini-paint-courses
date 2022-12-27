package courses.paint.mini.mapper.game;

import courses.paint.mini.dto.game.CourseMiniatureDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MiniatureDtoMapperTest {

    private final MiniatureDtoMapperImpl miniatureDtoMapper = new MiniatureDtoMapperImpl();

    @Test
    public void shouldMapCourseMiniatureDtoToMiniatureCorrectly() {
        // given
        var courseMiniatureDto = new CourseMiniatureDto("54", "mini", "range", "elves", "wh");

        // when
        var result = miniatureDtoMapper.toMiniature(courseMiniatureDto);

        // then
        assertEquals(courseMiniatureDto.getId(), result.getId());
        assertEquals(courseMiniatureDto.getName(), result.getName());
        assertEquals(courseMiniatureDto.getFactionName(), result.getFaction().getName());
        assertEquals(courseMiniatureDto.getType(), result.getType());
    }

}