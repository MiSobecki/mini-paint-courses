package courses.paint.mini.controller.user;

import com.jayway.jsonpath.JsonPath;
import courses.paint.mini.repository.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    private final static String USER_REGISTER_BODY = """
            {
                "username": "testUser",
                "password": "passwd"
            }
            """;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldRegisterUserCorrectly() throws Exception {
        // when
        var result = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER_REGISTER_BODY));

        var resultId = String.valueOf(
                JsonPath.read(
                        result.andReturn()
                                .getResponse()
                                .getContentAsString(),
                        "id").toString());

        var createdUser = userRepository.findById(resultId).get();

        // then
        result.andExpect(status().isCreated());

        assertEquals("testUser", createdUser.getUsername());
        assertTrue(passwordEncoder.matches("passwd", createdUser.getPassword()));
    }

}