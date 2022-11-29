package courses.paint.mini;

import courses.paint.mini.exception.user.NotFoundRoleException;
import courses.paint.mini.model.Role;
import courses.paint.mini.usecase.user.CreateRoleUseCase;
import courses.paint.mini.usecase.user.GetRoleByNameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class BasicRolesInitialization {
    private final CreateRoleUseCase createRoleUseCase;
    private final GetRoleByNameUseCase getRoleByNameUseCase;

    @PostConstruct
    public void createBasicRoles() {
        try {
            getRoleByNameUseCase.execute("USER");
        } catch (NotFoundRoleException ex) {
            var userRole = new Role(null, "USER");
            createRoleUseCase.execute(userRole);
        }

        try {
            getRoleByNameUseCase.execute("ADMIN");
        } catch (NotFoundRoleException ex) {
            var adminRole = new Role(null, "ADMIN");
            createRoleUseCase.execute(adminRole);
        }
    }

}
