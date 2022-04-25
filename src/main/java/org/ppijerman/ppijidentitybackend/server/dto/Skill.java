package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Table(name = "\"Skill\"", schema = "CENSUS")
public class Skill {
    @Id
    @Column(name = "skill_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID skillId;

    @Column(name = "skill_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String skillName;

    @Column(name = "skill_is_available", columnDefinition = "boolean", nullable = false)
    private boolean skillIsAvailable;

    @ManyToOne(optional = false)
    @JoinColumn(name = "skill_person_id", columnDefinition = "UUID", nullable = false, updatable = false)
    private Person skillPerson;

    @ManyToOne
    @JoinColumn(name = "skill_category_id", columnDefinition = "UUID", nullable = false)
    private Category skillCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Skill skill = (Skill) o;
        return skillId != null && Objects.equals(skillId, skill.skillId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
