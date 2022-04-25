package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Table(name = "\"Education\"", schema = "CENSUS")
public class Education {
    @Id
    @Column(name = "education_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID educationId;

    @Column(name = "education_start_date", columnDefinition = "DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar educationStartDate;

    @Column(name = "education_end_date", columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private Calendar educationEndDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "education_institution_id", columnDefinition = "UUID", nullable = false)
    private Institution educationInstitution;

    @ManyToOne(optional = false)
    @JoinColumn(name = "education_person_id", columnDefinition = "UUID", nullable = false, updatable = false)
    private Person educationPerson;

    @ManyToOne(optional = false)
    @JoinColumn(name = "education_degree_id", columnDefinition = "UUID", nullable = false)
    private Degree educationDegree;

    @ManyToOne(optional = false)
    @JoinColumn(name = "education_major_id", columnDefinition = "UUID", nullable = false)
    private Major educationMajor;

    @OneToMany(mappedBy = "fundingEducation", orphanRemoval = true)
    @ToString.Exclude
    private List<Funding> educationFundings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Education education = (Education) o;
        return educationId != null && Objects.equals(educationId, education.educationId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
