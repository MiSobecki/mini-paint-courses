package courses.paint.mini.entity.course;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "painting_technique")
public class PaintingTechniqueEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(length = 20, nullable = false)
    private String name;

}
