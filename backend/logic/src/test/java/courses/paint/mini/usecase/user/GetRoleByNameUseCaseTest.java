package courses.paint.mini.usecase.user;

import courses.paint.mini.exception.user.NotFoundRoleException;
import courses.paint.mini.model.Role;
import courses.paint.mini.port.RequestRolePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class GetRoleByNameUseCaseTest {

    @Mock
    private RequestRolePort requestRolePort;

    @InjectMocks
    private GetRoleByNameUseCase getRoleByNameUseCase;

    @Test
    public void shouldGetRoleCorrectly() {
        // given
        var name = "role";

        var role = new Role("546g56", "ROLE");
        doReturn(Optional.of(role)).when(requestRolePort).getRoleByNameIgnoreCase(name);

        // when
        var result = getRoleByNameUseCase.execute(name);

        // then
        assertEquals(name.toLowerCase(), result.getName().toLowerCase());
    }

    @Test(expected = NotFoundRoleException.class)
    public void shouldThrowNotFoundRoleWhileRoleOfGivenNameDoesNotExist() {
        // given
        var name = "role";

        doReturn(Optional.empty()).when(requestRolePort).getRoleByNameIgnoreCase(name);

        // when
        getRoleByNameUseCase.execute(name);
    }

}