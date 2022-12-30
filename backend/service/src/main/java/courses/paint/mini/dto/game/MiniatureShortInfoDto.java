package courses.paint.mini.dto.game;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class MiniatureShortInfoDto {

    private String id;
    private String name;
    private String type;

}
