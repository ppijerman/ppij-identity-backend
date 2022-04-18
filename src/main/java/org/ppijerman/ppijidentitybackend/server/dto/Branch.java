package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "Branch", schema = "CENSUS")
public class Branch {
    @Id
    @Column(name = "branch_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID branchId;

    @Column(name = "branch_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String branchName;

    @Column(name = "branch_email", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String branchEmail;

    @OneToOne(optional = false)
    @JoinColumn(name = "branch_leader_id", nullable = false)
    private Person branchLeader;

    @Column(name = "branch_phone", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String branchPhone;

    @Column(name = "branch_website", columnDefinition = "VARCHAR(50)", length = 50)
    private String branchWebsite;

    @Column(name = "branch_facebook", columnDefinition = "VARCHAR(50)", length = 50)
    private String branchFacebook;

    @Column(name = "branch_instagram", columnDefinition = "VARCHAR(50)", length = 50)
    private String branchInstagram;

    @Column(name = "branch_twitter", columnDefinition = "VARCHAR(50)", length = 50)
    private String branchTwitter;

}
