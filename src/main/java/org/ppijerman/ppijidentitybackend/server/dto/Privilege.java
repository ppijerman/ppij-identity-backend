package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Privilege\"", schema = "CENSUS")
public class Privilege {
    @Id
    @Column(name = "privilege_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID privilegeId;

    @Column(name = "privilege_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String privilegeName;
}
