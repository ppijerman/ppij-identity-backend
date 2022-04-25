package org.ppijerman.ppijidentitybackend.server.dto;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"Ip_Trial_Log\"", schema = "CENSUS_SECURITY")
public class IpTrialLog {
    @Id
    @Column(name = "ip_trial_log_address", columnDefinition = "INET")
    @ColumnTransformer(
            read = "HOST(ip_trial_log_address)",
            write = "CAST(? AS INET)"
    )
    private String ipTrialLogAddress;

    @Column(name = "ip_trial_log_count", columnDefinition = "SMALLINT default 0")
    private short ipTrialLogCount;
}
