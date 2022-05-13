package org.ppijerman.ppijidentitybackend.server.data.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"Experience\"", schema = "CENSUS")
public class Experience {
    @Id
    @GeneratedValue
    @Column(name = "experience_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID experienceId;

    @Column(name = "experience_name", columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
    private String experienceName;

    @Column(name = "experience_is_international", columnDefinition = "boolean", nullable = false)
    private String experienceIsInternational;

    @Column(name = "experience_start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date experienceStartDate;

    @Column(name = "experience_end_date")
    @Temporal(TemporalType.DATE)
    private Date experienceEndDate;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "experience_person_id", nullable = false, updatable = false)
    private Person experiencePerson;

    @ManyToOne
    @JoinColumn(name = "experience_category_id")
    private Category experienceCategory;

    @ManyToOne
    @JoinColumn(name = "experience_type_id", nullable = false)
    private ExperienceType experienceType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Experience that = (Experience) o;
        return experienceId != null && Objects.equals(experienceId, that.experienceId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
