package courses.paint.mini.mapper;

import courses.paint.mini.dto.UserDto;
import courses.paint.mini.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    User toUser(UserDto userDto);

    UserDto fromUser(User user);

}
