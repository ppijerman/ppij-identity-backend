package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "Funding", schema = "CENSUS")
public class Funding {
    @Id
    @Column(name = "funding_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID fundingId;

    @Column(name = "funding_type", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String fundingType;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "funding_institution_id", nullable = false)
    private Institution fundingInstitution;
}
