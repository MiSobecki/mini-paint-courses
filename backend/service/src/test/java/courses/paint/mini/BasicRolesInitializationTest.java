package courses.paint.mini;

import courses.paint.mini.model.Role;
import courses.paint.mini.model.User;
import courses.paint.mini.service.UserService;
import courses.paint.mini.usecase.user.CreateRoleUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasicRolesInitializationTest {

    @Mock
    private UserService userService;
    @Mock
    private CreateRoleUseCase createRoleUseCase;

    @InjectMocks
    private BasicRolesInitialization basicRolesInitialization;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    public void shouldCreateBasicRolesAndAdminUserCorrectly() {
        // given
        var userRole = new Role(null, "USER");
        doReturn(userRole).when(createRoleUseCase).execute(userRole);

        var adminRole = new Role(null, "ADMIN");
        doReturn(adminRole).when(createRoleUseCase).execute(adminRole);

        // when
        basicRolesInitialization.createBasicRolesAndAdminUser();

        // then
        verify(createRoleUseCase, times(2)).execute(any(Role.class));

        verify(userService).register(userCaptor.capture());
        var user = userCaptor.getValue();
        assertEquals(List.of("USER", "ADMIN"), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

    }

}