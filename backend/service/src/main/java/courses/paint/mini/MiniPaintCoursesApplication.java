package courses.paint.mini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:mini-paint-courses-service.properties")
public class MiniPaintCoursesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniPaintCoursesApplication.class, args);
    }

}
