package courses.paint.mini.usecase.course;

import courses.paint.mini.model.User;
import courses.paint.mini.model.course.Course;
import courses.paint.mini.model.game.Miniature;
import courses.paint.mini.port.CommandCoursePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateCourseUseCaseTest {

    @Mock
    private GetCourseByIdUseCase getCourseByIdUseCase;
    @Mock
    private CommandCoursePort commandCoursePort;

    @InjectMocks
    private UpdateCourseUseCase updateCourseUseCase;

    @Captor
    private ArgumentCaptor<String> titleCaptor;
    @Captor
    private ArgumentCaptor<String> descCaptor;

    @Test
    public void shouldUpdateCourseCorrectly() {
        // given
        var courseId = "123";
        var course = new Course(
                courseId,
                "test",
                "shortDesc",
                new HashSet<>(),
                new Miniature(),
                new User("234",
                        "testUser",
                        "pass",
                        new HashSet<>()));

        var existingCourse = mock(Course.class);
        doReturn(existingCourse).when(getCourseByIdUseCase).execute(courseId);

        // when
        updateCourseUseCase.execute(course, courseId);

        // then
        verify(existingCourse).setTitle(titleCaptor.capture());
        assertEquals(course.getTitle(), titleCaptor.getValue());
        verify(existingCourse).setShortDescription(descCaptor.capture());
        assertEquals(course.getShortDescription(), descCaptor.getValue());

        verify(existingCourse, never()).setMiniature(any());
        verify(existingCourse, never()).setId(any());
        verify(existingCourse, never()).setUser(any());
    }

}