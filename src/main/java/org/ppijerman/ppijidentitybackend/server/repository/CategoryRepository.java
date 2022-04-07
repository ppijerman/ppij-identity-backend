package org.ppijerman.ppijidentitybackend.server.repository;

import java.util.UUID;
import org.ppijerman.ppijidentitybackend.server.dto.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
}
