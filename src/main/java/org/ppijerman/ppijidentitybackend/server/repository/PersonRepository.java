package org.ppijerman.ppijidentitybackend.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ppijerman.ppijidentitybackend.server.dto.Person;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID>{
    
}
