package courses.paint.mini.usecase;

import courses.paint.mini.exception.course.NonUniqueCourseException;
import courses.paint.mini.model.User;
import courses.paint.mini.model.course.Course;
import courses.paint.mini.port.CommandCoursePort;
import courses.paint.mini.port.RequestCoursePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CreateCourseUseCaseTest {

    @Mock
    private RequestCoursePort requestCoursePort;
    @Mock
    private CommandCoursePort commandCoursePort;

    @InjectMocks
    private CreateCourseUseCase createCourseUseCase;

    @Test(expected = NonUniqueCourseException.class)
    public void shouldThrowNonUniqueCourseExceptionWhileGivenCourseHasTitleLikeOneOfThisUserAnotherCourse() {
        // given
        var course = new Course(
                "123",
                "test",
                new HashSet<>(),
                null,
                new User("234",
                        "testUser",
                        "pass"));

        doReturn(Optional.of(course)).when(requestCoursePort).getByTitleAndUserId(
                course.getTitle(),
                course.getUser().getId());

        // when
        createCourseUseCase.execute(course);
    }

}