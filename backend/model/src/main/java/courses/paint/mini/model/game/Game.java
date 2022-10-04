package courses.paint.mini.model.game;

import courses.paint.mini.constraint.GameType;
import courses.paint.mini.model.Producer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
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
