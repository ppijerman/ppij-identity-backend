package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="FUNDING", schema="CENSUS")
public class Funding {
    @Id
    @Column(name="funding_id", columnDefinition = "uuid")
    private UUID fundingID;

    @Column(name="funding_type", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String fundingType;

    @OneToOne
    @JoinColumn(name = "institute")
    private Institute institute;
}
