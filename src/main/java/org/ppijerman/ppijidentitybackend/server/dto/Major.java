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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"Major\"", schema = "CENSUS")
public class Major {
    @Id
    @GeneratedValue
    @Column(name = "major_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID majorId;

    @Column(name = "major_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String majorName;

    @OneToMany(mappedBy = "educationMajor")
    @ToString.Exclude
    private List<Education> educations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Major major = (Major) o;
        return majorId != null && Objects.equals(majorId, major.majorId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
