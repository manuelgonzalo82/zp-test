package manuelgonzalo.zooplustest.account.repository;

import manuelgonzalo.zooplustest.account.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by manuel on 24/6/17.
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
