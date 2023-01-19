package courses.paint.mini.adapter;

import courses.paint.mini.mapper.course.CourseStepImageMapper;
import courses.paint.mini.model.course.CourseStepImage;
import courses.paint.mini.port.CommandCourseStepImagePort;
import courses.paint.mini.repository.course.CourseStepImageRepository;
import courses.paint.mini.service.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandCourseStepImageAdapter implements CommandCourseStepImagePort {

    private final CourseStepImageRepository courseStepImageRepository;
    private final CourseStepImageMapper courseStepImageMapper;

    @Override
    public CourseStepImage create(CourseStepImage courseStepImage) {
        var imageEntity = courseStepImageMapper.fromCourseStepImage(courseStepImage);
        imageEntity.setImageData(ImageUtil.compressImage(imageEntity.getImageData()));

        imageEntity = courseStepImageRepository.save(imageEntity);

        imageEntity.setImageData(ImageUtil.decompressImage(imageEntity.getImageData()));
        return courseStepImageMapper.toCourseStepImage(imageEntity);
    }

    @Override
    public void delete(String id) {
        courseStepImageRepository.deleteByCourseStepId(id);
    }

}
