package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IpTrialLog {
    @Id
    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "trial_count", nullable = false)
    private int trialCount;
}
