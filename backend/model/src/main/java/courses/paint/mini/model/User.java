package courses.paint.mini.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "username"})
public class User {

    private String id;
    private String username;
    private String password;

}
