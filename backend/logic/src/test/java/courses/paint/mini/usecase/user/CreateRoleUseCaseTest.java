package courses.paint.mini.usecase.user;

import courses.paint.mini.exception.user.NonUniqueRoleException;
import courses.paint.mini.model.Role;
import courses.paint.mini.port.CommandRolePort;
import courses.paint.mini.port.RequestRolePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateRoleUseCaseTest {

    @Mock
    private CommandRolePort commandRolePort;
    @Mock
    private RequestRolePort requestRolePort;

    @InjectMocks
    private CreateRoleUseCase createRoleUseCase;

    @Captor
    private ArgumentCaptor<String> roleNameCaptor;

    @Test(expected = NonUniqueRoleException.class)
    public void shouldThrowNonUniqueRoleExceptionWhileRoleOfGivenNameAlreadyExists() {
        // given
        var role = new Role(null, "ROLE");

        var existingRole = new Role("fsv543", "ROLE");
        doReturn(Optional.of(existingRole)).when(requestRolePort).getRoleByNameIgnoreCase(role.getName());

        // when
        createRoleUseCase.execute(role);
    }

    @Test
    public void shouldCreateRoleCorrectlyWithUppercaseName() {
        // given
        var role = mock(Role.class);
        doReturn("Role").when(role).getName();

        doReturn(Optional.empty()).when(requestRolePort).getRoleByNameIgnoreCase("Role");

        // when
        createRoleUseCase.execute(role);

        // then
        verify(role).setName(roleNameCaptor.capture());
        assertEquals("ROLE", roleNameCaptor.getValue());
    }

}