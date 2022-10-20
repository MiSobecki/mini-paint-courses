package courses.paint.mini.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "username"})
public class UserDto {

    @NotBlank(message = "User id is mandatory")
    private String id;
    private String username;

}
