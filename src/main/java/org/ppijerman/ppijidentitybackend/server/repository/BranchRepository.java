package org.ppijerman.ppijidentitybackend.server.repository;

import org.ppijerman.ppijidentitybackend.server.dto.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    
}
