package courses.paint.mini.entity.course;

import courses.paint.mini.entity.UserEntity;
import courses.paint.mini.entity.game.MiniatureEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(length = 30, nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "course", fetch = FetchType.LAZY)
    private Set<CourseStepEntity> steps;

    @ManyToOne
    @JoinColumn(name = "miniature_id")
    private MiniatureEntity miniature;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
