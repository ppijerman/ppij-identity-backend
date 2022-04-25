package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Major\"", schema = "CENSUS")
public class Major {
    @Id
    @Column(name = "major_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID majorId;

    @Column(name = "major_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String majorName;

    @OneToMany(mappedBy = "educationMajor")
    private List<Education> educations;
}
