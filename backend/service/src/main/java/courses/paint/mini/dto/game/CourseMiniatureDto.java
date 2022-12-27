package courses.paint.mini.dto.game;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class CourseMiniatureDto {

    @NotBlank(message = "Miniature id is mandatory")
    private String id;
    private String name;
    private String type;
    private String factionName;
    private String gameTitle;

}
