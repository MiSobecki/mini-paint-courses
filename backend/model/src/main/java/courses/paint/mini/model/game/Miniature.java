package courses.paint.mini.model.game;

import courses.paint.mini.model.game.Faction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Miniature {

    private String id;
    private String name;
    private String type;
    private Faction faction;

}
