package courses.paint.mini.dto.game;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class FactionShortInfoDto {

    private String id;
    private String name;

}
