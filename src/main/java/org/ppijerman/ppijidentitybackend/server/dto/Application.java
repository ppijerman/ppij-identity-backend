package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Application\"", schema = "CENSUS")
public class Application {
    @Id
    @Column(name = "application_id", columnDefinition = "uuid default uuid_generate_v4()")
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
    @JoinColumn(name = "application_owner_id", nullable = false)
    private Person applicationOwner;

    @ManyToMany
    @JoinTable(
            name = "\"Privilege_Application_Map\"",
            schema = "CENSUS",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private List<Privilege> applicationPrivilege;
}
