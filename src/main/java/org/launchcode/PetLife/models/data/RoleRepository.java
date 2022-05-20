package org.launchcode.PetLife.models.data;

import org.launchcode.PetLife.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long > {
    Role findByName(String role_admin);
}
