package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Degree\"", schema = "CENSUS")
public class Degree {
    @Id
    @Column(name = "degree_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID degreeId;

    @Column(name = "degree_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String degreeName;

    @OneToMany(mappedBy = "educationDegree")
    private List<Education> educations;
}
