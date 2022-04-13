package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name="REFRESH_TOKEN", schema="CENSUS")
public class RefreshToken {
    @Id
    @Column(name="refresh_token", columnDefinition = "VARCHAR(64)", length = 64)
    private String refreshToken;

//  check if run
    @Column(name="expiration_time", columnDefinition = "time without time zone", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp expirationTime;

    @ManyToOne
    @JoinColumn(name = "application_id", columnDefinition = "UUID", nullable = false)
    private Application application;

}