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
@Table(name = "\"Access_Token\"", schema = "CENSUS")
public class AccessToken {
    @Id
    @Column(name = "access_token", columnDefinition = "VARCHAR(64)", updatable = false)
    private String accessToken;

    @Column(name = "access_token_expiration_time", columnDefinition = "TIMESTAMP WITHOUT TIMEZONE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar accessTokenExpirationTime;

    @OneToOne(optional = false)
    @JoinColumn(name = "access_token_application_id", columnDefinition = "UUID", nullable = false, updatable = false)
    private Application accessTokenApplication;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccessToken that = (AccessToken) o;
        return accessToken != null && Objects.equals(accessToken, that.accessToken);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
