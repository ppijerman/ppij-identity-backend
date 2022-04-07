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
    @Column(name="branch_id", columnDefinition = "uuid")
    private UUID branchID;

    @Column(name="branch_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String branchName;

    @Column(name="email", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String email;

    @Column(name="leader", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String leader;

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
