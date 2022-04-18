package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "Skill", schema = "CENSUS")
public class Skill {
    @Id
    @Column(name = "skill_id", columnDefinition = "uuid")
    private UUID skillID;

    @Column(name = "skill_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String skillName;

    @Column(name = "skill_is_available", columnDefinition = "boolean", nullable = false)
    private boolean skillIsAvailable;

    @ManyToOne(optional = false)
    @JoinColumn(name = "skill_person_id", nullable = false)
    private Person skillPerson;

    @ManyToOne
    @JoinColumn(name = "skill_category_id")
    private Category skillCategory;
}
