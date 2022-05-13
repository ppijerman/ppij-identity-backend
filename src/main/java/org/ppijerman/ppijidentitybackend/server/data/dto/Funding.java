package org.ppijerman.ppijidentitybackend.server.data.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"Funding\"", schema = "CENSUS")
public class Funding {
    @Id
    @GeneratedValue
    @Column(name = "funding_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID fundingId;

    @Column(name = "funding_type", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String fundingType;

    @Column(name = "funding_institution", columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
    private String fundingInstitution;

    @ManyToOne
    @JoinColumn(name = "funding_education_id", nullable = false, updatable = false)
    private Education fundingEducation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Funding funding = (Funding) o;
        return fundingId != null && Objects.equals(fundingId, funding.fundingId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
