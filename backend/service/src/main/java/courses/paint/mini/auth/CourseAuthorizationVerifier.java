package courses.paint.mini.auth;

import courses.paint.mini.exception.ForbiddenCourseActionException;
import courses.paint.mini.usecase.course.GetCourseByCourseStepIdUseCase;
import courses.paint.mini.usecase.course.GetCourseByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseAuthorizationVerifier {

    private final GetCourseByIdUseCase getCourseByIdUseCase;
    private final GetCourseByCourseStepIdUseCase getCourseByCourseStepIdUseCase;
    private final AuthenticationFacade authenticationFacade;

    public void verifyPermissionsToCourse(String courseId) {
        var course = getCourseByIdUseCase.execute(courseId);

        checkPermissionsToCourse(course.getUser().getUsername());
    }

    public void verifyPermissionsToCourseByCourseStepId(String courseStepId) {
        var course = getCourseByCourseStepIdUseCase.execute(courseStepId);

        checkPermissionsToCourse(course.getUser().getUsername());
    }

    private void checkPermissionsToCourse(String courseUsername) {
        var auth = authenticationFacade.getAuthentication();

        var authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        if (!authorities.contains("ADMIN") && !courseUsername.equals(auth.getName())) {
            throw new ForbiddenCourseActionException("User is not author of target course");
        }

    }
}
