package courses.paint.mini.entity.product;

import courses.paint.mini.constraint.PaintType;
import courses.paint.mini.entity.ProducerEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "paint")
public class PaintEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(length = 20, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaintType type;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private ProducerEntity producer;

}
