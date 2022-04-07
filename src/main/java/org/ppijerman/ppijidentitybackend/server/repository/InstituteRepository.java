package org.ppijerman.ppijidentitybackend.server.repository;

import java.util.UUID;

import org.ppijerman.ppijidentitybackend.server.dto.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepository extends JpaRepository<Institute,UUID>{
    
}
