package org.ppijerman.ppijidentitybackend.server.data.repository;

import org.ppijerman.ppijidentitybackend.server.data.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    public Role getRoleByRoleName(String roleName);
}
