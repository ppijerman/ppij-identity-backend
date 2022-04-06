package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="ACCESS_TOKEN", schema="CENSUS")
public class AccessToken {
    @Id
    @Column(name="access_token", columnDefinition = "VARCHAR(64)")
    private String accessToken;

    @Column(name="expiration_time", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp expirationTime;

    @OneToMany(mappedBy = "application_id")
    private List<Application> applications;

}
