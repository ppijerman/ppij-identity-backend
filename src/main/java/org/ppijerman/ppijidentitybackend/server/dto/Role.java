package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name="ROLE", schema="CENSUS")
public class Role {
    @Id
    @Column(name="role_id", columnDefinition = "UUID")
    private UUID roleId;

    @Column(name="role_name", columnDefinition="VARCHAR(20)", length = 20, nullable=false)
    private String roleName;
}
