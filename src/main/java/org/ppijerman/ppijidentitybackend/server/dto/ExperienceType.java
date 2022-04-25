package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Experience_Type\"", schema = "CENSUS")
public class ExperienceType {
    @Id
    @Column(name = "experience_type_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID experienceTypeId;

    @Column(name = "experience_type_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String experienceTypeName;

    @OneToMany(mappedBy = "experienceType")
    private List<Experience> experiences;
}
