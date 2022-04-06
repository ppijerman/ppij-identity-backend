package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="SKILL", schema="CENSUS")
public class Skill {
    @Id
    @Column(name="skill_id", columnDefinition = "uuid")
    private UUID skillID;

    @Column(name="skill_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String skillName;

    @Column(name="is_available", columnDefinition="boolean", nullable=false)
    private String isAvailable;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
