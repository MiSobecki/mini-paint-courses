package courses.paint.mini.config;

import courses.paint.mini.config.course.CourseConfig;
import courses.paint.mini.config.user.UserConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import({CourseConfig.class, UserConfig.class})
public class MiniPaintCoursesConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
