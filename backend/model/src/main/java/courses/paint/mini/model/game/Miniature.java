package courses.paint.mini.model.game;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class Miniature {

    private String id;
    private String name;
    private String type;
    private Faction faction;

}
