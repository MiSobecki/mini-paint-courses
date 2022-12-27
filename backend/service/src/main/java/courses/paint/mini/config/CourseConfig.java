package courses.paint.mini.config;

import courses.paint.mini.port.CommandCoursePort;
import courses.paint.mini.port.RequestCoursePort;
import courses.paint.mini.usecase.course.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CourseConfig {

    private final RequestCoursePort requestCoursePort;
    private final CommandCoursePort commandCoursePort;

    @Bean
    public GetAllCoursesFilteredUseCase getAllCoursesFilteredUseCase() {
        return new GetAllCoursesFilteredUseCase(requestCoursePort);
    }

    @Bean
    public GetCourseByIdUseCase getCourseByIdUseCase() {
        return new GetCourseByIdUseCase(requestCoursePort);
    }

    @Bean
    public CreateCourseUseCase createCourseUseCase() {
        return new CreateCourseUseCase(requestCoursePort, commandCoursePort);
    }

    @Bean
    public UpdateCourseUseCase updateCourseUseCase() {
        return new UpdateCourseUseCase(getCourseByIdUseCase(), commandCoursePort);
    }

    @Bean
    public DeleteCourseUseCase deleteCourseUseCase() {
        return new DeleteCourseUseCase(getCourseByIdUseCase(), commandCoursePort);
    }

}
