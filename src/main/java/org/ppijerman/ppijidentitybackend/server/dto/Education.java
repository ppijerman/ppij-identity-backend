package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
@Table(name="EDUCATION", schema="CENSUS")
public class Education {
    //    TODO: Generated Value or use setter?
    @Id
    private int education_id;

    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private Calendar start_date;

    @Temporal(TemporalType.DATE)
    private Calendar end_date;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institute institute_id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person_id;

    @OneToOne
    @JoinColumn(name = "degree_id")
    private Degree degree_id;


}
