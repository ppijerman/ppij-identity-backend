package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="ACCESS_TOKEN", schema="CENSUS")
public class AccessToken {
    @Id
    @Column(name="access_token", columnDefinition = "VARCHAR(64)", length = 64)
    private String accessToken;

//    check if run
    @Column(name="expiration_time", columnDefinition = "time without time zone", nullable=false)
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp expirationTime;

    @ManyToOne
    @JoinColumn(name = "application_id", columnDefinition = "UUID", nullable = false)
    private Application application;

}
