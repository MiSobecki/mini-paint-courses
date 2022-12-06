package courses.paint.mini.controller.course;

import courses.paint.mini.dto.course.CourseDto;
import courses.paint.mini.dto.course.CourseShortInfoDto;
import courses.paint.mini.dto.course.CourseUpdateDto;
import courses.paint.mini.mapper.course.CourseDtoMapper;
import courses.paint.mini.model.course.CourseFilters;
import courses.paint.mini.usecase.course.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final GetAllCoursesFilteredUseCase getAllCoursesFilteredUseCase;
    private final GetCourseByIdUseCase getCourseByIdUseCase;
    private final CreateCourseUseCase createCourseUseCase;
    private final UpdateCourseUseCase updateCourseUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;
    private final CourseDtoMapper courseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public CourseDto create(@RequestBody @Valid CourseDto courseDto) {
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
                                                   @PageableDefault Pageable pageable) {
        if (courseFilters == null) {
            courseFilters = createEmptyFilters();
        }

        var coursesFiltered = getAllCoursesFilteredUseCase.execute(courseFilters, pageable);

        return coursesFiltered.map(courseMapper::shortInfoFromCourse);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public CourseDto update(@PathVariable String id,
                            @RequestBody @Valid CourseUpdateDto courseUpdateDto) {
        var course = courseMapper.fromCourseUpdateDto(courseUpdateDto);
        course = updateCourseUseCase.execute(course, id);

        return courseMapper.fromCourse(course);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        deleteCourseUseCase.execute(id);
    }

    private CourseFilters createEmptyFilters() {
        return new CourseFilters(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

}
