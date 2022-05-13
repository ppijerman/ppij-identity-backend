package org.ppijerman.ppijidentitybackend.server.data.repository;

import org.ppijerman.ppijidentitybackend.server.data.dto.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
}
