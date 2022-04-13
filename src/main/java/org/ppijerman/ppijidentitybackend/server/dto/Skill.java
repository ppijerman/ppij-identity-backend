package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="SKILL", schema="CENSUS")
public class Skill {
    @Id
    @Column(name="skill_id", columnDefinition = "UUID")
    private UUID skillId;

    @Column(name="skill_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String skillName;

    @Column(name="is_available", columnDefinition="BOOLEAN", nullable=false)
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "person_id", columnDefinition="UUID", nullable=false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "category_id", columnDefinition="UUID", nullable=false)
    private Category category;
}
