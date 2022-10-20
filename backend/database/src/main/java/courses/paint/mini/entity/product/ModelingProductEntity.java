package courses.paint.mini.entity.product;

import courses.paint.mini.entity.ProducerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "modeling_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModelingProductEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 50)
    private String category;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private ProducerEntity producer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ModelingProductEntity that = (ModelingProductEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
