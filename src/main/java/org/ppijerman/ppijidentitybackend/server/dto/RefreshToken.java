package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
@Table(name = "\"Refresh_Token\"", schema = "CENSUS")
public class RefreshToken {
    @Id
    @Column(name = "refresh_token", columnDefinition = "VARCHAR(64)", updatable = false)
    private String refreshToken;

    @Column(name = "refresh_token_expiration_time", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar refreshTokenExpirationTime;

    @OneToOne(optional = false)
    @JoinColumn(name = "refresh_token_application_id", columnDefinition = "UUID", nullable = false)
    private Application refreshTokenApplication;
}