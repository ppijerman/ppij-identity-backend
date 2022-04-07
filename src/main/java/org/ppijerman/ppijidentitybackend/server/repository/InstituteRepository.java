package org.ppijerman.ppijidentitybackend.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ppijerman.ppijidentitybackend.server.dto.Institute;
import java.util.UUID;

public interface InstituteRepository extends JpaRepository<Institute,UUID>{
    
}
