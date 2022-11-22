package courses.paint.mini.usecase.user;

import courses.paint.mini.exception.user.NotFoundUserException;
import courses.paint.mini.model.User;
import courses.paint.mini.port.RequestUserPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class GetUserByUsernameUseCaseTest {

    @Mock
    private RequestUserPort requestUserPort;

    @InjectMocks
    private GetUserByUsernameUseCase getUserByUsernameUseCase;

    @Test
    public void shouldGetUserCorrectly() {
        // given
        var username = "user";

        var user = new User("5346gfd", "user", "passwd", new HashSet<>());
        doReturn(Optional.of(user)).when(requestUserPort).getByUsername(username);

        // when
        var result = getUserByUsernameUseCase.execute(username);

        // then
        assertEquals(username, result.getUsername());
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getPassword(), result.getPassword());
    }

    @Test(expected = NotFoundUserException.class)
    public void shouldThrowNotFoundUserExceptionWhileUserOfGivenUsernameDoesNotExist() {
        // given
        var username = "user";

        doReturn(Optional.empty()).when(requestUserPort).getByUsername(username);

        // when
        getUserByUsernameUseCase.execute(username);
    }

}