package courses.paint.mini.dto.course;

import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "orderNumber"})
public class CourseStepDto {

    private String id;
    private Long orderNumber;
    private String title;
    private String description;
    private Map<String, String> paintTechniqueIdToPaintIdMap;
    private Set<String> usedOtherModelingProductIds;

}
