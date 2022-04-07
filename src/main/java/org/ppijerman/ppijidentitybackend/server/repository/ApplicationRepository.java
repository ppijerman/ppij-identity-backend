package org.ppijerman.ppijidentitybackend.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.ppijerman.ppijidentitybackend.server.dto.Application;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    
}
