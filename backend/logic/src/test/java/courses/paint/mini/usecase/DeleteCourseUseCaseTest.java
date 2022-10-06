package courses.paint.mini.usecase;

import courses.paint.mini.exception.course.NonExistingCourseException;
import courses.paint.mini.port.CommandCoursePort;
import courses.paint.mini.port.RequestCoursePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class DeleteCourseUseCaseTest {

    @Mock
    private RequestCoursePort requestCoursePort;
    @Mock
    private CommandCoursePort commandCoursePort;

    @InjectMocks
    private DeleteCourseUseCase deleteCourseUseCase;

    @Test(expected = NonExistingCourseException.class)
    public void shouldThrowNonExistingCourseExceptionWhileCourseOfGivenIdDoesNotExist() {
        // given
        var courseId = "123";

        doReturn(Optional.empty()).when(requestCoursePort).getById(courseId);

        // when
        deleteCourseUseCase.execute(courseId);
    }

}