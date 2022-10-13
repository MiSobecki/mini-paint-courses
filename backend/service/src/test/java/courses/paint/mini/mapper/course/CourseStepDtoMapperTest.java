package courses.paint.mini.mapper.course;

import courses.paint.mini.constraint.PaintType;
import courses.paint.mini.dto.course.CourseStepDto;
import courses.paint.mini.model.Producer;
import courses.paint.mini.model.course.CourseStep;
import courses.paint.mini.model.course.PaintingTechnique;
import courses.paint.mini.model.product.ModelingProduct;
import courses.paint.mini.model.product.Paint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CourseStepDtoMapperTest {

    private final CourseStepDtoMapperImpl courseStepDtoMapper = new CourseStepDtoMapperImpl();

    @Test
    public void shouldMapCourseStepDtoToCourseStepCorrectly() {
        // given
        var courseStepDto = new CourseStepDto(
                "4342",
                1L,
                "first Step",
                "desc",
                Map.ofEntries(
                        Map.entry("key1", "val1")
                ),
                Set.of("5345", "2455"));

        // when
        var result = courseStepDtoMapper.toCourseStep(courseStepDto);

        // then
        assertEquals(courseStepDto.getId(), result.getId());
        assertEquals(courseStepDto.getDescription(), result.getDescription());
        assertEquals(courseStepDto.getTitle(), result.getTitle());
        assertEquals(courseStepDto.getOrderNumber(), result.getOrderNumber());
        assertEquals(
                courseStepDto.getUsedOtherModelingProductIds(),
                result.getUsedOtherModelingProducts().stream()
                        .map(ModelingProduct::getId)
                        .collect(Collectors.toSet()));
        assertEquals(
                courseStepDto.getPaintTechniqueIdToPaintIdMap(),
                result.getUsedPaints().entrySet().stream()
                        .map(x -> Map.entry(x.getKey().getId(), x.getValue().getId()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    @Test
    public void shouldMapCourseStepToCourseStepDtoCorrectly() {
        // given
        var producer = new Producer("543", "GW");

        var courseStep = new CourseStep(
                "4342",
                1L,
                "first Step",
                "desc",
                Map.ofEntries(
                        Map.entry(
                                new Paint(
                                        "543",
                                        "paint",
                                        PaintType.BASE,
                                        producer),
                                new PaintingTechnique(
                                        "543654",
                                        "basing"))
                ),
                Set.of(
                        new ModelingProduct(
                                "78553",
                                "grain",
                                "base",
                                producer)));

        // when
        var result = courseStepDtoMapper.fromCourseStep(courseStep);

        // then
        assertEquals(courseStep.getId(), result.getId());
        assertEquals(courseStep.getDescription(), result.getDescription());
        assertEquals(courseStep.getTitle(), result.getTitle());
        assertEquals(courseStep.getOrderNumber(), result.getOrderNumber());
        assertEquals(
                courseStep.getUsedOtherModelingProducts()
                        .stream()
                        .map(ModelingProduct::getId)
                        .collect(Collectors.toSet()),
                result.getUsedOtherModelingProductIds());
        assertEquals(
                courseStep.getUsedPaints()
                        .entrySet().stream()
                        .map(x -> Map.entry(x.getKey().getId(), x.getValue().getId()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)),
                result.getPaintTechniqueIdToPaintIdMap());
    }

}