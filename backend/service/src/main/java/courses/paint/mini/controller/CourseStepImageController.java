package courses.paint.mini.controller;

import courses.paint.mini.auth.CourseAuthorizationVerifier;
import courses.paint.mini.dto.course.CourseStepImageDto;
import courses.paint.mini.exception.WrongFileFormatException;
import courses.paint.mini.mapper.course.CourseStepImageDtoMapper;
import courses.paint.mini.usecase.course.coursestep.DeleteImageUseCase;
import courses.paint.mini.usecase.course.coursestep.DownloadImagesUseCase;
import courses.paint.mini.usecase.course.coursestep.UploadImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/course-step-images")
@RequiredArgsConstructor
public class CourseStepImageController {
    private final CourseStepImageDtoMapper courseStepImageDtoMapper;
    private final DownloadImagesUseCase downloadImagesUseCase;
    private final UploadImageUseCase uploadImageUseCase;
    private final DeleteImageUseCase deleteImageUseCase;
    private final CourseAuthorizationVerifier courseAuthorizationVerifier;

    @GetMapping("/{courseStepId}")
    public List<CourseStepImageDto> downloadByCourseStepId(@PathVariable String courseStepId) {
        return downloadImagesUseCase.execute(courseStepId).stream()
                .map(courseStepImageDtoMapper::fromCourseStepImage)
                .toList();
    }

    @PostMapping("/{courseStepId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public CourseStepImageDto upload(@PathVariable String courseStepId,
                                     @RequestParam MultipartFile image) throws IOException {
        courseAuthorizationVerifier.verifyPermissionsToCourseByCourseStepId(courseStepId);

        if (ImageIO.read(image.getInputStream()) == null) {
            throw new WrongFileFormatException();
        }

        var courseStepImage = uploadImageUseCase.execute(courseStepId, image.getBytes());

        return courseStepImageDtoMapper.fromCourseStepImage(courseStepImage);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void delete(@PathVariable String id) {
        courseAuthorizationVerifier.verifyPermissionsToCourseByCourseStepId(id);

        deleteImageUseCase.execute(id);
    }

}
