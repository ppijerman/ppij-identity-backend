package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Funding\"", schema = "CENSUS")
public class Funding {
    @Id
    @Column(name = "funding_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID fundingId;

    @Column(name = "funding_type", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String fundingType;

    @Column(name = "funding_institution", columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
    private String fundingInstitution;

    @ManyToOne
    @JoinColumn(name = "funding_education_id", nullable = false, updatable = false)
    private Education fundingEducation;
}
