package courses.paint.mini.controller.course;

import com.jayway.jsonpath.JsonPath;
import courses.paint.mini.entity.ProducerEntity;
import courses.paint.mini.entity.UserEntity;
import courses.paint.mini.entity.course.CourseEntity;
import courses.paint.mini.entity.course.CourseStepEntity;
import courses.paint.mini.entity.course.PaintingTechniqueEntity;
import courses.paint.mini.entity.game.FactionEntity;
import courses.paint.mini.entity.game.GameEntity;
import courses.paint.mini.entity.game.MiniatureEntity;
import courses.paint.mini.entity.product.ModelingProductEntity;
import courses.paint.mini.entity.product.PaintEntity;
import courses.paint.mini.enums.GameType;
import courses.paint.mini.enums.PaintType;
import courses.paint.mini.repository.ProducerRepository;
import courses.paint.mini.repository.UserRepository;
import courses.paint.mini.repository.course.CourseRepository;
import courses.paint.mini.repository.course.CourseStepRepository;
import courses.paint.mini.repository.course.PaintingTechniqueRepository;
import courses.paint.mini.repository.game.FactionRepository;
import courses.paint.mini.repository.game.GameRepository;
import courses.paint.mini.repository.game.MiniatureRepository;
import courses.paint.mini.repository.product.ModelingProductRepository;
import courses.paint.mini.repository.product.PaintRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@AutoConfigureDataJpa
@RunWith(SpringRunner.class)
public class CourseControllerIntegrationTest {

    private final static String COURSE_CREATE_BODY = """
            {
                "title": "course for chaos legionaire",
                "shortDescription": "short desc about course",
                "miniature": {
                    "id": "%s"
                },
                "user": {
                    "id": "%s"
                },
                "steps": [
                    {
                        "orderNumber": 1,
                        "title": "start basing",
                        "description": "lets highlight miniature with caledor sky base paint",
                        "paintTechniqueIdToPaintIdMap": {
                            "%s": "%s"
                        },
                        "usedOtherModelingProductIds": [
                            "%s"
                        ]
                    }
                ]
            }
            """;
    private final static String COURSE_UPDATE_BODY = """
            {
                "title": "course for chaos legionaire UPDATED",
                "shortDescription": "short desc about course UPDATED",
                "steps": [
                    {
                        "orderNumber": 1,
                        "title": "start priming",
                        "description": "lets prime miniature with caledor sky base paint",
                        "paintTechniqueIdToPaintIdMap": {
                            "%s": "%s"
                        },
                        "usedOtherModelingProductIds": [
                            "%s"
                        ]
                    }
                ]
            }
            """;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseStepRepository courseStepRepository;
    @Autowired
    private PaintingTechniqueRepository paintingTechniqueRepository;
    @Autowired
    private MiniatureRepository miniatureRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private FactionRepository factionRepository;
    @Autowired
    private PaintRepository paintRepository;
    @Autowired
    private ModelingProductRepository modelingProductRepository;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity;
    private ProducerEntity producerEntity;
    private ModelingProductEntity modelingProductEntity;
    private PaintEntity paintEntity;
    private FactionEntity factionEntity;
    private GameEntity gameEntity;
    private MiniatureEntity miniatureEntity;
    private PaintingTechniqueEntity paintingTechniqueEntity;

    @Before
    public void init() {
        userEntity = new UserEntity(null, "ms", "passwd");
        userEntity = userRepository.save(userEntity);

        producerEntity = new ProducerEntity(null, "GW");
        producerEntity = producerRepository.save(producerEntity);

        modelingProductEntity = new ModelingProductEntity(null, "sand", "basing", producerEntity);
        modelingProductEntity = modelingProductRepository.save(modelingProductEntity);

        paintEntity = new PaintEntity(null, "caledor sky", PaintType.BASE, producerEntity);
        paintEntity = paintRepository.save(paintEntity);

        miniatureEntity = new MiniatureEntity(null, "chaos legionaire", "melee", null);
        factionEntity = new FactionEntity(null, "chaos", Set.of(miniatureEntity), null);
        gameEntity = new GameEntity(null, "wh40k", Set.of(factionEntity), GameType.SCIENCE_FICTION, producerEntity);

        miniatureEntity = miniatureRepository.save(miniatureEntity);
        factionEntity = factionRepository.save(factionEntity);
        gameEntity = gameRepository.save(gameEntity);

        factionEntity.setGame(gameEntity);
        factionEntity = factionRepository.save(factionEntity);
        miniatureEntity.setFaction(factionEntity);
        miniatureEntity = miniatureRepository.save(miniatureEntity);

        paintingTechniqueEntity = new PaintingTechniqueEntity(null, "highlight");
        paintingTechniqueEntity = paintingTechniqueRepository.save(paintingTechniqueEntity);
    }

    @Test
    public void shouldCreateCourseCorrectly() throws Exception {
        // given
        var step = new CourseStepEntity(
                null,
                1L,
                "start basing",
                "lets highlight miniature with caledor sky base paint",
                Map.ofEntries(
                        Map.entry(paintEntity, paintingTechniqueEntity)
                ),
                Set.of(modelingProductEntity),
                null);

        // when
        var result = mockMvc.perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(COURSE_CREATE_BODY,
                        miniatureEntity.getId(),
                        userEntity.getId(),
                        paintEntity.getId(),
                        paintingTechniqueEntity.getId(),
                        modelingProductEntity.getId())));

        var resultId = String.valueOf(
                JsonPath.read(
                        result.andReturn()
                                .getResponse()
                                .getContentAsString(),
                        "id").toString());
        var resultStepId = String.valueOf(
                        JsonPath.read(
                                result.andReturn()
                                        .getResponse()
                                        .getContentAsString(),
                                "$.steps[:1].id").toString())
                .replace("[\"", "")
                .replace("\"]", "");

        var createdCourse = courseRepository.findById(resultId).get();
        var createdCourseStep = courseStepRepository.findById(resultStepId).get();

        // then
        result.andExpect(status().isCreated());

        assertEquals("course for chaos legionaire", createdCourse.getTitle());
        assertEquals("short desc about course", createdCourse.getShortDescription());

        assertEquals(userEntity, createdCourse.getUser());
        assertEquals(miniatureEntity, createdCourse.getMiniature());

        assertEquals(step.getDescription(), createdCourseStep.getDescription());
        assertEquals(step.getTitle(), createdCourseStep.getTitle());
        assertEquals(step.getOrderNumber(), createdCourseStep.getOrderNumber());
        assertEquals(step.getUsedOtherModelingProducts(), createdCourseStep.getUsedOtherModelingProducts());
        assertEquals(step.getUsedPaints(), createdCourseStep.getUsedPaints());
    }

    @Test
    public void shouldReturnConflictResponseWhileCourseOfGivenTitleAndFromGivenUserAlreadyExists() throws Exception {
        // given
        var courseEntity = new CourseEntity(
                null,
                "course for chaos legionaire",
                "shortDesc",
                new HashSet<>(),
                miniatureEntity,
                userEntity);
        courseEntity = courseRepository.save(courseEntity);

        // when
        var result = mockMvc.perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(COURSE_CREATE_BODY,
                        miniatureEntity.getId(),
                        userEntity.getId(),
                        paintEntity.getId(),
                        paintingTechniqueEntity.getId(),
                        modelingProductEntity.getId())));

        // then
        result.andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("errorMessage").value(
                        String.format("Course of the title: '%s' already exists for this user and has id: '%s'",
                                "course for chaos legionaire",
                                courseEntity.getId())));

    }

    @Test
    public void shouldGetCourseByIdCorrectly() throws Exception {
        // given
        var step = new CourseStepEntity(
                null,
                1L,
                "start basing",
                "lets highlight miniature with caledor sky base paint",
                Map.ofEntries(
                        Map.entry(paintEntity, paintingTechniqueEntity)
                ),
                Set.of(modelingProductEntity),
                null);

        var courseEntity = new CourseEntity(
                null,
                "course for chaos legionaire",
                "shortDesc",
                Set.of(step),
                miniatureEntity,
                userEntity);
        step.setCourse(courseEntity);
        courseEntity = courseRepository.save(courseEntity);

        // when
        var result = mockMvc.perform(get("/api/courses/" + courseEntity.getId()));

        // then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("id").value(courseEntity.getId()))
                .andExpect(jsonPath("title").value(courseEntity.getTitle()))
                .andExpect(jsonPath("shortDescription").value(courseEntity.getShortDescription()))
                .andExpect(jsonPath("steps", hasSize(1)))
                .andExpect(jsonPath("miniature.id").value(courseEntity.getMiniature().getId()))
                .andExpect(jsonPath("miniature.name").value(courseEntity.getMiniature().getName()))
                .andExpect(jsonPath("miniature.type").value(courseEntity.getMiniature().getType()))
                .andExpect(jsonPath("miniature.factionName").value("chaos"))
                .andExpect(jsonPath("user.id").value(courseEntity.getUser().getId()))
                .andExpect(jsonPath("user.username").value(courseEntity.getUser().getUsername()));
    }

    @Test
    public void shouldReturnNotFoundResponseWhileCourseOfGivenIdToGetDoesNotExist() throws Exception {
        // when
        var result = mockMvc.perform(get("/api/courses/765A"));

        // then
        result.andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("errorMessage").value("Course of id: '765A' does not exist"));
    }

    @Test
    public void shouldGetFilteredCoursesCorrectly() throws Exception {
        // given
        courseRepository.saveAll(createExampleCourses());

        // when
        var result = mockMvc.perform(get("/api/courses")
                .param("page", "0")
                .param("size", "10")
                .param("paintId", paintEntity.getId())
                .param("username", userEntity.getUsername())
                .param("miniatureProducerId", producerEntity.getId()));

        // then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("content.length()").value(1))
                .andExpect(jsonPath("content[0].title").value("thirdCourse"))
                .andExpect(jsonPath("content[0].miniatureName").value(miniatureEntity.getName()))
                .andExpect(jsonPath("content[0].factionName").value(factionEntity.getName()))
                .andExpect(jsonPath("content[0].gameTitle").value(gameEntity.getTitle()))
                .andExpect(jsonPath("content[0].username").value(userEntity.getUsername()));
    }

    @Test
    public void shouldUpdateCourseCorrectly() throws Exception {
        // given
        var step = new CourseStepEntity(
                null,
                1L,
                "start basing",
                "lets highlight miniature with caledor sky base paint",
                Map.ofEntries(
                        Map.entry(paintEntity, paintingTechniqueEntity)
                ),
                Set.of(modelingProductEntity),
                null);

        var courseEntity = new CourseEntity(
                null,
                "course for chaos legionaire",
                "shortDesc",
                Set.of(step),
                miniatureEntity,
                userEntity);
        step.setCourse(courseEntity);
        courseEntity = courseRepository.saveAndFlush(courseEntity);

        // when
        var result = mockMvc.perform(patch("/api/courses/" + courseEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(COURSE_UPDATE_BODY,
                        paintEntity.getId(),
                        paintingTechniqueEntity.getId(),
                        modelingProductEntity.getId())));

        // then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("id").value(courseEntity.getId()))
                .andExpect(jsonPath("title").value("course for chaos legionaire UPDATED"))
                .andExpect(jsonPath("shortDescription").value("short desc about course UPDATED"))
                .andExpect(jsonPath("steps", hasSize(1)))
                .andExpect(jsonPath("miniature.id").value(courseEntity.getMiniature().getId()))
                .andExpect(jsonPath("miniature.name").value(courseEntity.getMiniature().getName()))
                .andExpect(jsonPath("miniature.type").value(courseEntity.getMiniature().getType()))
                .andExpect(jsonPath("miniature.factionName").value("chaos"))
                .andExpect(jsonPath("user.id").value(courseEntity.getUser().getId()))
                .andExpect(jsonPath("user.username").value(courseEntity.getUser().getUsername()));
    }

    @Test
    public void shouldReturnNotFoundResponseWhileCourseOfGivenIdToUpdateDoesNotExist() throws Exception {
        // when
        var result = mockMvc.perform(patch("/api/courses/64565L")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(COURSE_UPDATE_BODY,
                        paintEntity.getId(),
                        paintingTechniqueEntity.getId(),
                        modelingProductEntity.getId())));

        // then
        result.andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("errorMessage").value("Course of id: '64565L' does not exist"));
    }

    @Test
    public void shouldDeleteCourseByIdCorrectly() throws Exception {
        // given
        var courseEntity = new CourseEntity(
                null,
                "course for chaos legionaire",
                "shortDesc",
                new HashSet<>(),
                miniatureEntity,
                userEntity);
        courseEntity = courseRepository.save(courseEntity);

        // when
        var result = mockMvc.perform(delete("/api/courses/" + courseEntity.getId()));

        // then
        result.andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnNotFoundResponseWhileCourseOfGivenIdToDeleteDoesNotExist() throws Exception {
        // when
        var result = mockMvc.perform(delete("/api/courses/5654GF"));

        // then
        result.andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("errorMessage").value("Course of id: '5654GF' does not exist"));
    }

    private List<CourseEntity> createExampleCourses() {
        var armyPainterProducer = new ProducerEntity(null, "Army Painter");
        armyPainterProducer = producerRepository.save(armyPainterProducer);

        var grainModelingProduct = new ModelingProductEntity(null, "grain", "base", armyPainterProducer);
        grainModelingProduct = modelingProductRepository.save(grainModelingProduct);

        var otherUser = new UserEntity(null, "blodig", "testpass");
        otherUser = userRepository.save(otherUser);

        var elvesMiniature = new MiniatureEntity(null, "Elves lord", "hero", null);
        elvesMiniature = miniatureRepository.save(elvesMiniature);

        var elvesFaction = new FactionEntity(null, "Elves", Set.of(elvesMiniature), null);
        elvesFaction = factionRepository.save(elvesFaction);

        elvesMiniature.setFaction(elvesFaction);
        elvesMiniature = miniatureRepository.save(elvesMiniature);

        var conquestGame = new GameEntity(null, "Conquest", Set.of(elvesFaction), GameType.FANTASY, armyPainterProducer);
        conquestGame = gameRepository.save(conquestGame);

        elvesFaction.setGame(conquestGame);
        factionRepository.save(elvesFaction);

        var thirdCourse = new CourseEntity(null,
                "thirdCourse",
                "desc",
                null,
                miniatureEntity,
                userEntity);

        var thirdCourseStep = new CourseStepEntity(null,
                1L,
                "Layering",
                "desc",
                Map.ofEntries(
                        Map.entry(paintEntity, paintingTechniqueEntity)
                ),
                Set.of(grainModelingProduct),
                thirdCourse);
        thirdCourse.setSteps(Set.of(thirdCourseStep));

        return List.of(
                new CourseEntity(null, "firstCourse", "desc", new HashSet<>(), miniatureEntity, userEntity),
                new CourseEntity(null, "secondCourse", "desc", new HashSet<>(), elvesMiniature, otherUser),
                thirdCourse,
                new CourseEntity(null, "fourthCourse", "desc", new HashSet<>(), elvesMiniature, userEntity)

        );
    }

}