package courses.paint.mini.usecase.course;

import courses.paint.mini.model.User;
import courses.paint.mini.model.course.Course;
import courses.paint.mini.model.course.CourseStep;
import courses.paint.mini.model.game.Miniature;
import courses.paint.mini.port.CommandCoursePort;
import courses.paint.mini.port.RequestCoursePort;
import courses.paint.mini.usecase.course.UpdateCourseUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    private ArgumentCaptor<Set<CourseStep>> stepsCaptor;

    @Test
    public void shouldUpdateCourseCorrectly() {
        // given
        var courseId = "123";
        var course = new Course(
                courseId,
                "test",
                new HashSet<>(),
                new Miniature(),
                new User("234",
                        "testUser",
                        "pass"));

        var existingCourse = mock(Course.class);
        doReturn(existingCourse).when(getCourseByIdUseCase).execute(courseId);

        // when
        updateCourseUseCase.execute(course, courseId);

        // then
        verify(existingCourse).setTitle(titleCaptor.capture());
        assertEquals(course.getTitle(), titleCaptor.getValue());
        verify(existingCourse).setSteps(stepsCaptor.capture());
        assertEquals(course.getSteps(), stepsCaptor.getValue());

        verify(existingCourse, never()).setMiniature(any());
        verify(existingCourse, never()).setId(any());
        verify(existingCourse, never()).setUser(any());
    }

}