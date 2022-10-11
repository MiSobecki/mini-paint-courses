package courses.paint.mini.model.game;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class Faction {

    private String id;
    private String name;
    private Set<Miniature> miniatures;
    private Game game;

}
