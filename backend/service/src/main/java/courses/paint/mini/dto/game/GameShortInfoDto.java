package courses.paint.mini.dto.game;

import courses.paint.mini.dto.ProducerDto;
import courses.paint.mini.enums.GameType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
public class GameShortInfoDto {

    private String id;
    private String title;
    private GameType type;
    private ProducerDto producer;

}
