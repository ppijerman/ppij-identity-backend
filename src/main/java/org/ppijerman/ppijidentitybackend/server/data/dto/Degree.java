package org.ppijerman.ppijidentitybackend.server.data.dto;

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
@Table(name = "\"Degree\"", schema = "CENSUS")
public class Degree {
    @Id
    @GeneratedValue
    @Column(name = "degree_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID degreeId;

    @Column(name = "degree_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String degreeName;

    @OneToMany(mappedBy = "educationDegree")
    @ToString.Exclude
    private List<Education> educations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Degree degree = (Degree) o;
        return degreeId != null && Objects.equals(degreeId, degree.degreeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
