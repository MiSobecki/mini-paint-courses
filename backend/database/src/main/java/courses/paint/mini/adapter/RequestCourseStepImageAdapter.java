package courses.paint.mini.adapter;

import courses.paint.mini.mapper.course.CourseStepImageMapper;
import courses.paint.mini.model.course.CourseStepImage;
import courses.paint.mini.port.RequestCourseStepImagePort;
import courses.paint.mini.repository.course.CourseStepImageRepository;
import courses.paint.mini.service.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RequestCourseStepImageAdapter implements RequestCourseStepImagePort {

    private final CourseStepImageRepository courseStepImageRepository;
    private final CourseStepImageMapper courseStepImageMapper;

    @Override
    public List<CourseStepImage> download(String courseStepId) {
        var images = courseStepImageRepository.findAllByCourseStepId(courseStepId).stream()
                .map(courseStepImageMapper::toCourseStepImage)
                .toList();

        images.forEach(x -> x.setImage(ImageUtil.decompressImage(x.getImage())));

        return images;
    }
}
