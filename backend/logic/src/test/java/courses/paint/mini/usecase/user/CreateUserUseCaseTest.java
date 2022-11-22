package courses.paint.mini.usecase.user;

import courses.paint.mini.exception.user.NonUniqueUserException;
import courses.paint.mini.model.Role;
import courses.paint.mini.model.User;
import courses.paint.mini.port.CommandUserPort;
import courses.paint.mini.port.RequestUserPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserUseCaseTest {

    @Mock
    private CommandUserPort commandUserPort;
    @Mock
    private GetRoleByNameUseCase getRoleByNameUseCase;
    @Mock
    private RequestUserPort requestUserPort;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Captor
    private ArgumentCaptor<Set<Role>> rolesCaptor;

    @Test
    public void shouldCreateUserWithUSERRoleCorrectly() {
        // given
        var user = spy(new User(null, "user", "passwd", null));

        var username = user.getUsername();
        doReturn(Optional.empty()).when(requestUserPort).getByUsername(username);

        var role = new Role("rv4665656", "USER");
        doReturn(role).when(getRoleByNameUseCase).execute("USER");

        // when
        createUserUseCase.execute(user);

        // then
        verify(user).setRoles(rolesCaptor.capture());
        assertTrue(rolesCaptor.getValue().stream()
                .allMatch(x -> x.getName().equals(role.getName()))
                && rolesCaptor.getValue().size() == 1);
    }

    @Test(expected = NonUniqueUserException.class)
    public void shouldThrowNonUniqueUserExceptionWhileUserOfGivenUsernameAlreadyExists() {
        // given
        var user = new User("fe5gy5", "user", "passwd", null);

        var username = user.getUsername();
        var existingUser = new User("fetsdvwaz", "user", "passwd", null);
        doReturn(Optional.of(existingUser)).when(requestUserPort).getByUsername(username);

        // when
        createUserUseCase.execute(user);
    }

}