package courses.paint.mini.controller.course;

import courses.paint.mini.dto.course.CourseDto;
import courses.paint.mini.dto.course.CourseShortInfoDto;
import courses.paint.mini.mapper.course.CourseDtoMapper;
import courses.paint.mini.model.course.CourseFilters;
import courses.paint.mini.usecase.course.CreateCourseUseCase;
import courses.paint.mini.usecase.course.DeleteCourseUseCase;
import courses.paint.mini.usecase.course.GetAllCoursesFilteredUseCase;
import courses.paint.mini.usecase.course.GetCourseByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final GetAllCoursesFilteredUseCase getAllCoursesFilteredUseCase;
    private final GetCourseByIdUseCase getCourseByIdUseCase;
    private final CreateCourseUseCase createCourseUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;
    private final CourseDtoMapper courseMapper;

    @PostMapping
    public CourseDto create(@RequestBody CourseDto courseDto) {
        var course = courseMapper.toCourse(courseDto);
        course = createCourseUseCase.execute(course);

        return courseMapper.fromCourse(course);
    }

    @GetMapping("/{id}")
    public CourseDto getById(@PathVariable String id) {
        var course = getCourseByIdUseCase.execute(id);

        return courseMapper.fromCourse(course);
    }

    @GetMapping
    public Page<CourseShortInfoDto> getAllFiltered(CourseFilters courseFilters,
                                                   Pageable pageable) {
        var coursesFiltered = getAllCoursesFilteredUseCase.execute(courseFilters, pageable);
        return coursesFiltered.map(courseMapper::shortInfoFromCourse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        deleteCourseUseCase.execute(id);
    }

}
