package courses.paint.mini.entity.game;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "faction")
public class FactionEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(length = 20, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "faction", fetch = FetchType.LAZY)
    private Set<MiniatureEntity> miniatures;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameEntity game;

}
