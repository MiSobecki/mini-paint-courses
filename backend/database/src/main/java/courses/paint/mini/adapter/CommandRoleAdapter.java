package courses.paint.mini.adapter;

import courses.paint.mini.mapper.RoleMapper;
import courses.paint.mini.model.Role;
import courses.paint.mini.port.CommandRolePort;
import courses.paint.mini.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandRoleAdapter implements CommandRolePort {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role create(Role role) {
        var roleEntity = roleMapper.fromRole(role);
        roleEntity = roleRepository.save(roleEntity);

        return roleMapper.toRole(roleEntity);
    }

    @Override
    public Role update(Role role) {
        var roleEntity = roleMapper.fromRole(role);
        roleEntity = roleRepository.save(roleEntity);

        return roleMapper.toRole(roleEntity);
    }

}
