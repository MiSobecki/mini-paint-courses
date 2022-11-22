package courses.paint.mini;

import courses.paint.mini.model.Role;
import courses.paint.mini.model.User;
import courses.paint.mini.service.UserService;
import courses.paint.mini.usecase.user.CreateRoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class BasicRolesInitialization {

    private final UserService userService;
    private final CreateRoleUseCase createRoleUseCase;

    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;

    @PostConstruct
    public void createBasicRolesAndAdminUser() {
        var userRole = new Role(null, "USER");
        userRole = createRoleUseCase.execute(userRole);

        var adminRole = new Role(null, "ADMIN");
        adminRole = createRoleUseCase.execute(adminRole);

        var adminUser = new User(null, adminUsername, adminPassword, Set.of(userRole, adminRole));

        userService.register(adminUser);
    }

}
