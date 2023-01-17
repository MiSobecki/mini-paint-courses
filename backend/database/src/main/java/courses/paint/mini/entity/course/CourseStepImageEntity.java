package courses.paint.mini.entity.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "course_step_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseStepImageEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_step_id")
    private CourseStepEntity courseStep;

    @Lob
    private byte[] imageData;

}
