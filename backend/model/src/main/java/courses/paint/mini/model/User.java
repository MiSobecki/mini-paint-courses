package courses.paint.mini.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "username"})
public class User {

    private String id;
    private String username;
    private String password;
    private Set<Role> roles;

}
