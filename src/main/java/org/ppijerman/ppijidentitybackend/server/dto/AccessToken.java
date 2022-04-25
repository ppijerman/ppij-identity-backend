package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
@Table(name = "\"Access_Token\"", schema = "CENSUS")
public class AccessToken {
    @Id
    @Column(name = "access_token", columnDefinition = "VARCHAR(64)", updatable = false)
    private String accessToken;

    @Column(name = "access_token_expiration_time", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar accessTokenExpirationTime;

    @OneToOne(optional = false)
    @JoinColumn(name = "access_token_application_id", columnDefinition = "UUID", nullable = false, updatable = false)
    private Application accessTokenApplication;
}
