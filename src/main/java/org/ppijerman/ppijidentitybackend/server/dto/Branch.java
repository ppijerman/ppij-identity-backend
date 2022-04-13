package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Data
@Table(name="BRANCH", schema="CENSUS")
public class Branch {
    @Id
    @Column(name="branch_id", columnDefinition = "UUID")
    private UUID branchId;

    @Column(name="branch_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String branchName;

    @Column(name="email", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String email;

    @OneToOne
    @JoinColumn(name="leader_id", columnDefinition="UUID", nullable=false)
    private Person leader;

    @Column(name="phone", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String phone;

    @Column(name="website", columnDefinition="VARCHAR(50)", length = 50)
    private String website;

    @Column(name="facebook", columnDefinition="VARCHAR(50)", length = 50)
    private String facebook;

    @Column(name="instagram", columnDefinition="VARCHAR(50)", length = 50)
    private String instagram;

    @Column(name="twitter", columnDefinition="VARCHAR(50)", length = 50)
    private String twitter;

}
