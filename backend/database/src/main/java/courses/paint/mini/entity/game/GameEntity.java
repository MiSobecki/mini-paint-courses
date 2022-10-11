package courses.paint.mini.entity.game;

import courses.paint.mini.constraint.GameType;
import courses.paint.mini.entity.ProducerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "game")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GameEntity that = (GameEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
