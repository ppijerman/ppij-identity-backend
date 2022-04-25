package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Table(name = "\"Experience_Type\"", schema = "CENSUS")
public class ExperienceType {
    @Id
    @Column(name = "experience_type_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID experienceTypeId;

    @Column(name = "experience_type_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String experienceTypeName;

    @OneToMany(mappedBy = "experienceType")
    @ToString.Exclude
    private List<Experience> experiences;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExperienceType that = (ExperienceType) o;
        return experienceTypeId != null && Objects.equals(experienceTypeId, that.experienceTypeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
