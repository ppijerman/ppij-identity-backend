package org.ppijerman.ppijidentitybackend.server.repository;

import org.ppijerman.ppijidentitybackend.server.dto.Person;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
=======
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, UUID>{
>>>>>>> feature/JPA-class
    
}
