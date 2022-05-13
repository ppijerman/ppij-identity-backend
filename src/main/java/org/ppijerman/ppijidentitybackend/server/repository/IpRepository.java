package org.ppijerman.ppijidentitybackend.server.repository;

import org.ppijerman.ppijidentitybackend.server.dto.IpTrialLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpRepository extends JpaRepository<IpTrialLog, String> {
}