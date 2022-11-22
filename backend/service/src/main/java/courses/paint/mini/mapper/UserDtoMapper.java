package courses.paint.mini.mapper;

import courses.paint.mini.dto.user.UserDto;
import courses.paint.mini.dto.user.UserRegisterDto;
import courses.paint.mini.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    User toUser(UserDto userDto);

    UserDto fromUser(User user);

    User fromUserRegisterDto(UserRegisterDto userRegisterDto);

}
