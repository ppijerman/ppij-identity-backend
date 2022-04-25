package org.ppijerman.ppijidentitybackend.server.dto;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
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
}
