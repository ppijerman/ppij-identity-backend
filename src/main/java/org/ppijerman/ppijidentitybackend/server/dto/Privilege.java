package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name="PRIVILEGE", schema="CENSUS")
public class Privilege {
    @Id
    @Column(name="privilege_id", columnDefinition = "uuid")
    private UUID privilegeID;

    @Column(name="privilege_name", columnDefinition="VARCHAR(20)", length = 20, nullable=false)
    private String privilegeName;
}
