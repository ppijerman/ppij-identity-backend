package org.ppijerman.ppijidentitybackend.server.data.repository;

import org.ppijerman.ppijidentitybackend.server.data.dto.IpTrialLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository extends JpaRepository<IpTrialLog, String> {
}