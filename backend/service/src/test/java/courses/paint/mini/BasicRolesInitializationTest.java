package courses.paint.mini;

import courses.paint.mini.exception.user.NotFoundRoleException;
import courses.paint.mini.model.Role;
import courses.paint.mini.usecase.user.CreateRoleUseCase;
import courses.paint.mini.usecase.user.GetRoleByNameUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasicRolesInitializationTest {
    @Mock
    private CreateRoleUseCase createRoleUseCase;
    @Mock
    private GetRoleByNameUseCase getRoleByNameUseCase;

    @InjectMocks
    private BasicRolesInitialization basicRolesInitialization;

    @Test
    public void shouldCreateBasicRoles() {
        // given
        var userRole = new Role(null, "USER");
        doReturn(userRole).when(createRoleUseCase).execute(userRole);

        var adminRole = new Role(null, "ADMIN");
        doReturn(adminRole).when(createRoleUseCase).execute(adminRole);

        doThrow(NotFoundRoleException.class).when(getRoleByNameUseCase).execute(anyString());

        // when
        basicRolesInitialization.createBasicRoles();

        // then
        verify(createRoleUseCase, times(2)).execute(any(Role.class));

    }

}