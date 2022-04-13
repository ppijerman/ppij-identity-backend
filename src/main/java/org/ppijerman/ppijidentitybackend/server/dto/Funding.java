package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="FUNDING", schema="CENSUS")
public class Funding {
    @Id
    @Column(name="funding_id", columnDefinition = "UUID")
    private UUID fundingId;

    @Column(name="funding_type", columnDefinition="VARCHAR", nullable=false)
    private String fundingType;

    @Column(name="funding_name", columnDefinition="VARCHAR", nullable=false)
    private String fundingName;

//    @OneToOne
//    @JoinColumn(name = "institute")
//    private Institute institute;
}
