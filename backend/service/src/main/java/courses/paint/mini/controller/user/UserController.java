package courses.paint.mini.controller.user;

import courses.paint.mini.dto.user.UserDto;
import courses.paint.mini.dto.user.UserRegisterDto;
import courses.paint.mini.mapper.UserDtoMapper;
import courses.paint.mini.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDtoMapper userMapper;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        var user = userMapper.fromUserRegisterDto(userRegisterDto);

        user = userService.register(user);

        return userMapper.fromUser(user);
    }

}
