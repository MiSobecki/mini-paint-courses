package courses.paint.mini.adapter;

import courses.paint.mini.mapper.RoleMapper;
import courses.paint.mini.model.Role;
import courses.paint.mini.port.RequestRolePort;
import courses.paint.mini.repository.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestRoleAdapter implements RequestRolePort {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Optional<Role> getRoleByNameIgnoreCase(String name) {
        var roleEntity = roleRepository.findByNameIgnoreCase(name);

        return roleEntity.map(roleMapper::toRole);
    }

    @Override
    public Optional<Role> getRoleById(String id) {
        var roleEntity = roleRepository.findById(id);

        return roleEntity.map(roleMapper::toRole);
    }
}
