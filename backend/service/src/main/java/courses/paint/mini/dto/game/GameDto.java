package courses.paint.mini.dto.game;

import courses.paint.mini.enums.GameType;
import courses.paint.mini.dto.ProducerDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
public class GameDto {

    private String id;
    private String title;
    private Set<FactionDto> factions;
    private GameType type;
    private ProducerDto producer;

}
