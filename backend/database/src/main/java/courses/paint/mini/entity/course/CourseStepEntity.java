package courses.paint.mini.entity.course;

import courses.paint.mini.entity.product.ModelingProductEntity;
import courses.paint.mini.entity.product.PaintEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "course_step")
public class CourseStepEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private Long orderNumber;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "paint_technique_mapping",
            joinColumns = {@JoinColumn(name = "course_step_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "technique_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "paint_id")
    private Map<PaintEntity, PaintingTechniqueEntity> usedPaints;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_step_modeling_product",
            joinColumns = {@JoinColumn(name = "course_step_id")},
            inverseJoinColumns = {@JoinColumn(name = "modeling_product_id")}
    )
    private Set<ModelingProductEntity> usedOtherModelingProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

}
