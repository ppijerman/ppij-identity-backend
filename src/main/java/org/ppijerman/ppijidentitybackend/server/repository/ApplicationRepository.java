package org.ppijerman.ppijidentitybackend.server.repository;

<<<<<<< HEAD
public interface ApplicationRepository {
=======
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.ppijerman.ppijidentitybackend.server.dto.Application;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {
>>>>>>> feature/JPA-class
    
}