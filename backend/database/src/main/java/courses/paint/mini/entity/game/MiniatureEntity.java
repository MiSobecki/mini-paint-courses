package courses.paint.mini.entity.game;

import courses.paint.mini.model.game.Faction;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "miniature")
public class MiniatureEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faction_id")
    private FactionEntity faction;

}
