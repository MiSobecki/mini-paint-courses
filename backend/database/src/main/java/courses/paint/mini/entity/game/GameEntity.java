package courses.paint.mini.entity.game;

import courses.paint.mini.constraint.GameType;
import courses.paint.mini.entity.ProducerEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "game")
public class GameEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(length = 20, nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "game", fetch = FetchType.LAZY)
    private Set<FactionEntity> factions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameType type;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private ProducerEntity producer;

}
