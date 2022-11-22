package courses.paint.mini.usecase.user;

import courses.paint.mini.exception.user.NotFoundRoleByIdException;
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
public class GetRoleByIdUseCaseTest {

    @Mock
    private RequestRolePort requestRolePort;

    @InjectMocks
    private GetRoleByIdUseCase getRoleByIdUseCase;

    @Test
    public void shouldGetRoleCorrectly() {
        // given
        var id = "35653dsgd";

        var role = new Role("35653dsgd", "ROLE");
        doReturn(Optional.of(role)).when(requestRolePort).getRoleById(id);

        // when
        var result = getRoleByIdUseCase.execute(id);

        // then
        assertEquals(id, result.getId());
    }

    @Test(expected = NotFoundRoleByIdException.class)
    public void shouldThrowNotFoundRoleWhileRoleOfGivenIdDoesNotExist() {
        // given
        var id = "35653dsgd";

        doReturn(Optional.empty()).when(requestRolePort).getRoleById(id);

        // when
        getRoleByIdUseCase.execute(id);
    }

}