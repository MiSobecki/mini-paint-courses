package courses.paint.mini.mapper;

import courses.paint.mini.entity.RoleEntity;
import courses.paint.mini.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toRole(RoleEntity roleEntity);

    RoleEntity fromRole(Role role);

}
