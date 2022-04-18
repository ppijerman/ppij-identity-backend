package org.ppijerman.ppijidentitybackend.server.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ip_Trial_Log")
public class IpTrialLog {
    @Id
    @Column(name = "ip_trial_log_address")
    private String ipTrialLogAddress;

    @Column(name = "ip_trial_log_count", nullable = false)
    private int ipTrialLogCount;
}
