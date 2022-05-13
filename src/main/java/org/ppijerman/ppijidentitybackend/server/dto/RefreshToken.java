package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"Refresh_Token\"", schema = "CENSUS")
public class RefreshToken {
    @Id
    @Column(name = "refresh_token", columnDefinition = "VARCHAR(64)", updatable = false)
    private String refreshToken;

    @Column(name = "refresh_token_expiration_time", columnDefinition = "TIMESTAMP WITHOUT TIMEZONE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar refreshTokenExpirationTime;

    @OneToOne(optional = false)
    @JoinColumn(name = "refresh_token_application_id", columnDefinition = "UUID", nullable = false)
    private Application refreshTokenApplication;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RefreshToken that = (RefreshToken) o;
        return refreshToken != null && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}