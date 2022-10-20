package courses.paint.mini.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class ProducerDto {

    private String id;
    private String name;

}
