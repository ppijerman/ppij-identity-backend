package org.ppijerman.ppijidentitybackend.server.data.dto;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "ip_trial_log_last_timestamp", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ipTrialLogLastTimestamp;

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
