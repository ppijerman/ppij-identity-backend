package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Experience_Type\"", schema = "CENSUS")
public class ExperienceType {
    @Id
    @Column(name = "experience_type_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID experienceTypeId;

    @Column(name = "experience_type_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String experienceTypeName;
}
