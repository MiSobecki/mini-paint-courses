package courses.paint.mini.config;

import courses.paint.mini.config.course.CourseConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CourseConfig.class)
public class MiniPaintCoursesConfig {

}
