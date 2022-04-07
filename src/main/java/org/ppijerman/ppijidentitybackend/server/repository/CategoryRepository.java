package org.ppijerman.ppijidentitybackend.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.ppijerman.ppijidentitybackend.server.dto.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
}
