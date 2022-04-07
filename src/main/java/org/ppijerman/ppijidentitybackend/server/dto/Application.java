package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="Application", schema="CENSUS")
public class Application {
    @Id
    @Column(name="application_id", columnDefinition = "uuid")
    private UUID applicationID;

    @Column(name="application_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String applicationName;

    @Column(name = "application_secret_key", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String applicationSecretKey;

    @Column(name="application_description", columnDefinition="VARCHAR(255)", length = 255, nullable=false)
    private String applicationDescription;

    @Column(name="creation_date", nullable=false)
    @Temporal(TemporalType.DATE)
    private Calendar creationDate;

    @OneToMany(mappedBy = "application_owner_id")
    private List<Person> ownerList;

}
