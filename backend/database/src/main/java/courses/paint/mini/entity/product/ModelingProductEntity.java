package courses.paint.mini.entity.product;

import courses.paint.mini.entity.ProducerEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "modeling_product")
public class ModelingProductEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20)
    private String category;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private ProducerEntity producer;

}
