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
    @Column(name="refresh_token", columnDefinition = "VARCHAR(64)")
    private String refreshToken;

    @Column(name="expiration_time", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp expirationTime;

    @OneToMany
    @JoinColumn(name = "application_id")
    private List<Application> applications;

}