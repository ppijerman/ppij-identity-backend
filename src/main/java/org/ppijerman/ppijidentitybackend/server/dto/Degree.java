package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Degree\"", schema = "CENSUS")
public class Degree {
    // TODO: Generated Value or use setter?
    @Id
    @Column(name = "degree_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID degreeId;

    @Column(name = "degree_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String degreeName;
}
