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
    private UUID educationId;

    @Column(name="start_date", columnDefinition = "DATE", nullable=false)
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column(name="end_date", columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private Calendar endDate;


    @ManyToOne
    @JoinColumn(name = "person_id", columnDefinition = "UUID", nullable=false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "degree_id", columnDefinition = "UUID", nullable=false)
    private Degree degree;

    @ManyToOne
    @JoinColumn(name = "major_id", columnDefinition = "UUID", nullable=false)
    private Major major;

    @ManyToOne
    @JoinColumn(name = "funding_id", columnDefinition = "UUID", nullable=false)
    private Funding funding;

    @ManyToOne
    @JoinColumn(name = "institute_id", columnDefinition = "UUID", nullable=false)
    private Institute institute;

}
