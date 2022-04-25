package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Table(name = "\"Ip_Trial_Log\"", schema = "CENSUS_SECURITY")
public class IpTrialLog {
    @Id
    @Column(name = "ip_trial_log_address", columnDefinition = "INET", updatable = false)
    @ColumnTransformer(
            read = "HOST(ip_trial_log_address)",
            write = "CAST(? AS INET)"
    )
    private String ipTrialLogAddress;

    @Column(name = "ip_trial_log_count", columnDefinition = "SMALLINT default 0", nullable = false)
    private short ipTrialLogCount;

    @Column(name = "ip_trial_log_last_timestamp", columnDefinition = "TIMESTAMP WITH TIMEZONE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp ipTrialLogLastTimestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IpTrialLog that = (IpTrialLog) o;
        return ipTrialLogAddress != null && Objects.equals(ipTrialLogAddress, that.ipTrialLogAddress);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
