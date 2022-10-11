package courses.paint.mini.model.game;

import courses.paint.mini.model.game.Faction;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Miniature {

    private String id;
    private String name;
    private String type;
    private Faction faction;

}
