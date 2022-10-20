package courses.paint.mini.model.game;

import courses.paint.mini.enums.GameType;
import courses.paint.mini.model.Producer;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "title"})
public class Game {

    private String id;
    private String title;
    private Set<Faction> factions;
    private GameType type;
    private Producer producer;


}
