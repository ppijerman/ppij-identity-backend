package org.ppijerman.ppijidentitybackend.server.data.repository;

import org.ppijerman.ppijidentitybackend.server.data.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    public Person findByPersonEmail(String email);
}
