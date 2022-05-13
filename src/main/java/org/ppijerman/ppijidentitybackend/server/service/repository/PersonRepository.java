package org.ppijerman.ppijidentitybackend.server.service.repository;

import org.ppijerman.ppijidentitybackend.server.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
}
