package org.ppijerman.ppijidentitybackend.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ppijerman.ppijidentitybackend.server.dto.Branch;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {

}
    

