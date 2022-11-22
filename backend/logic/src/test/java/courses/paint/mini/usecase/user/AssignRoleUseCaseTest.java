package courses.paint.mini.usecase.user;

import courses.paint.mini.model.Role;
import courses.paint.mini.model.User;
import courses.paint.mini.port.CommandUserPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AssignRoleUseCaseTest {

    @Mock
    private CommandUserPort commandUserPort;
    @Mock
    private GetUserByUsernameUseCase getUserByUsernameUseCase;
    @Mock
    private GetRoleByIdUseCase getRoleByIdUseCase;

    @InjectMocks
    private AssignRoleUseCase assignRoleUseCase;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    public void shouldAssignRoleCorrectly() {
        // given
        var username = "user";
        var roleId = "436b5g3qa3";

        var user = new User("esb6e6", username, "passwd", new HashSet<>());
        doReturn(user).when(getUserByUsernameUseCase).execute(username);

        var role = new Role(roleId, "ADMIN");
        doReturn(role).when(getRoleByIdUseCase).execute(roleId);

        // when
        assignRoleUseCase.execute(username, roleId);

        // then
        verify(commandUserPort).update(userCaptor.capture());
        assertTrue(userCaptor.getValue().getRoles().stream()
                .anyMatch(x -> x.getId().equals(roleId)));

    }

}