package courses.paint.mini.dto.course;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "orderNumber"})
public class CourseStepDto {

    private String id;
    @Positive(message = "Order number should be positive value")
    private Long orderNumber;
    @NotBlank(message = "Course step title is mandatory")
    @Size(max = 100, message = "Course step title should has size of max 100 characters")
    private String title;
    @Size(max = 1000, message = "Course step description should has size of max 1000 characters")
    private String description;
    private Map<String, String> paintTechniqueIdToPaintIdMap;
    private Set<String> usedOtherModelingProductIds;

}
