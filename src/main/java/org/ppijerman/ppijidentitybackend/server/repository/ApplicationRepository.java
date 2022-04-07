package org.ppijerman.ppijidentitybackend.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ppijerman.ppijidentitybackend.server.dto.Application;
import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    
}
