package org.ppijerman.ppijidentitybackend.server.repository;

<<<<<<< HEAD
import org.ppijerman.ppijidentitybackend.server.dto.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    
}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.ppijerman.ppijidentitybackend.server.dto.Branch;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {

}
    

>>>>>>> feature/JPA-class
