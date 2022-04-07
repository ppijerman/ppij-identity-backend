package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="EDUCATION", schema="CENSUS")
public class Education {
    @Id
    @Column(name="education_id", columnDefinition = "uuid")
    private UUID education_id;

    @Column(name="start_date", nullable=false)
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column(name="end_date")
    @Temporal(TemporalType.DATE)
    private Calendar endDate;


    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute instituteID;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person personID;

    @OneToOne
    @JoinColumn(name = "degree_id")
    private Degree degreeID;

    @OneToMany
    @JoinColumn(name = "major_id")
    private List<Major> major;

    @OneToOne
    @JoinColumn(name = "funding_id")
    private Funding funding;

}
