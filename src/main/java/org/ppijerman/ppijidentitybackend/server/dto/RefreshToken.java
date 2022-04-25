package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Data
@Table(name = "\"Refresh_Token\"", schema = "CENSUS")
public class RefreshToken {
    @Id
    @Column(name = "refresh_token", columnDefinition = "VARCHAR(64)")
    private String refreshToken;

    @Column(name = "refresh_token_expiration_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar refreshTokenExpirationTime;

    @OneToOne(optional = false)
    @JoinColumn(name = "refresh_token_application_id", nullable = false)
    private Application refreshTokenApplication;
}