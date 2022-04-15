package org.ppijerman.ppijidentitybackend.server;
import org.ppijerman.ppijidentitybackend.server.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    @Query("SELECT u FROM Person u WHERE u.email = ?1")
    public Person findByEmail(String email);
}
