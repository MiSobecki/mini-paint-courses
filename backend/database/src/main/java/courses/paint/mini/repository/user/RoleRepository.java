package courses.paint.mini.repository.user;

import courses.paint.mini.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    Optional<RoleEntity> findByNameIgnoreCase(String name);

}
