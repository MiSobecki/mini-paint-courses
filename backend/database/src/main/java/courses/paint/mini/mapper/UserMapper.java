package courses.paint.mini.mapper;

import courses.paint.mini.entity.UserEntity;
import courses.paint.mini.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserEntity fromUser(User user);

    User toUser(UserEntity userEntity);

}
