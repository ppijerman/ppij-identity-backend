package org.ppijerman.ppijidentitybackend.server.repository;

import org.ppijerman.ppijidentitybackend.server.dto.Person;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, UUID>{
    
}
