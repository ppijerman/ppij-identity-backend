package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name="EXPERIENCE_TYPE", schema="CENSUS")
public class ExperienceType {
    @Id
    @Column(name="experience_type_id", columnDefinition = "uuid")
    private UUID experienceTypeID;

    @Column(name="experience_type_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String experienceTypeName;
}