package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
@Table(name="BRANCH", schema="CENSUS")
public class Branch {
    // TODO: Generated Value or use setter?
    @Id
    private int branch_iD;

    @Column(length = 50, nullable=false)
    private String branch_name;

    @Column(length = 50, nullable=false)
    private String email;

    @Column(length = 50, nullable=false)
    private String leader;

    @Column(length = 50, nullable=false)
    private String phone;

    @Column(length = 50)
    private String website;

    @Column(length = 50)
    private String facebook;

    @Column(length = 50)
    private String instagram;

    @Column(length = 50)
    private String twitter;

}
