package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Education", schema = "CENSUS")
public class Education {
    @Id
    @Column(name = "education_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID educationId;

    @Column(name = "education_start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar educationStartDate;

    @Column(name = "education_end_date")
    @Temporal(TemporalType.DATE)
    private Calendar educationEndDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "education_institution_id", nullable = false)
    private Institution educationInstitution;

    @ManyToOne(optional = false)
    @JoinColumn(name = "education_person_id", nullable = false)
    private Person educationPerson;

    @OneToOne(optional = false)
    @JoinColumn(name = "education_degree_id", nullable = false)
    private Degree educationDegree;

    @OneToMany(targetEntity = Major.class)
    @JoinColumn(name = "education_major_id", nullable = false)
    private List<Major> educationMajor;

    @OneToOne(optional = false)
    @JoinColumn(name = "education_funding_id", nullable = false)
    private Funding educationFunding;

}
