package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Table(name = "\"Application\"", schema = "CENSUS")
public class Application {
    @Id
    @Column(name = "application_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID applicationId;

    @Column(name = "application_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String applicationName;

    @Column(name = "application_secret_key", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    @ColumnTransformer(
            write = "crypt(?, gen_salt('bf'))"
    )
    private String applicationSecretKey;

    @Column(name = "application_description", columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
    private String applicationDescription;

    @Column(name = "application_creation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar creationDate;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "application_owner_id", nullable = false, updatable = false)
    private Person applicationOwner;

    @ManyToMany
    @JoinTable(
            name = "\"Privilege_Application_Map\"",
            schema = "CENSUS",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    @ToString.Exclude
    private List<Privilege> applicationPrivilege;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Application that = (Application) o;
        return applicationId != null && Objects.equals(applicationId, that.applicationId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
