package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Table(name = "\"Branch\"", schema = "CENSUS")
public class Branch {
    @Id
    @Column(name = "branch_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID branchId;

    @Column(name = "branch_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String branchName;

    @Column(name = "branch_email", columnDefinition = "VARCHAR(100)", length = 50, nullable = false)
    private String branchEmail;

    @OneToOne(optional = false)
    @JoinColumn(name = "branch_leader_id", columnDefinition = "BYTEA", nullable = false)
    private Person branchLeader;

    @Column(name = "branch_phone", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String branchPhone;

    @Column(name = "branch_website", columnDefinition = "VARCHAR(50)", length = 50)
    private String branchWebsite;

    @Column(name = "branch_facebook_page", columnDefinition = "VARCHAR(100)", length = 100)
    private String branchFacebookPage;

    @Column(name = "branch_instagram", columnDefinition = "VARCHAR(50)", length = 50)
    private String branchInstagram;

    @Column(name = "branch_twitter", columnDefinition = "VARCHAR(50)", length = 50)
    private String branchTwitter;

    @OneToMany(mappedBy = "personBranch")
    @ToString.Exclude
    private List<Person> members;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Branch branch = (Branch) o;
        return branchId != null && Objects.equals(branchId, branch.branchId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
