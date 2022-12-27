package courses.paint.mini.config;

import courses.paint.mini.port.CommandRolePort;
import courses.paint.mini.port.CommandUserPort;
import courses.paint.mini.port.RequestRolePort;
import courses.paint.mini.port.RequestUserPort;
import courses.paint.mini.usecase.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserConfig {

    private final CommandUserPort commandUserPort;
    private final CommandRolePort commandRolePort;
    private final RequestUserPort requestUserPort;
    private final RequestRolePort requestRolePort;

    @Bean
    public GetRoleByNameUseCase getRoleByNameUseCase() {
        return new GetRoleByNameUseCase(requestRolePort);
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(commandUserPort, getRoleByNameUseCase(), requestUserPort);
    }

    @Bean
    public GetUserByUsernameUseCase getUserByUsernameUseCase() {
        return new GetUserByUsernameUseCase(requestUserPort);
    }

    @Bean
    public GetRoleByIdUseCase getRoleByIdUseCase() {
        return new GetRoleByIdUseCase(requestRolePort);
    }

    @Bean
    public AssignRoleUseCase assignRoleUseCase() {
        return new AssignRoleUseCase(commandUserPort, getUserByUsernameUseCase(), getRoleByIdUseCase());
    }

    @Bean
    public CreateRoleUseCase createRoleUseCase() {
        return new CreateRoleUseCase(commandRolePort, requestRolePort);
    }

}
