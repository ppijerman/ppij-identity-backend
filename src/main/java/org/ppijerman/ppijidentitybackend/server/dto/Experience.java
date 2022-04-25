package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Experience\"", schema = "CENSUS")
public class Experience {
    @Id
    @Column(name = "experience_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID experienceId;

    @Column(name = "experience_name", columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
    private String experienceName;

    @Column(name = "experience_is_international", columnDefinition = "boolean", nullable = false)
    private String experienceIsInternational;

    @Column(name = "experience_start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar experienceStartDate;

    @Column(name = "experience_end_date")
    @Temporal(TemporalType.DATE)
    private Calendar experienceEndDate;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "experience_person_id", nullable = false)
    private Person experiencePerson;

    @ManyToOne
    @JoinColumn(name = "experience_category_id")
    private Category experienceCategory;

    @ManyToOne
    @JoinColumn(name = "experience_type_id", nullable = false)
    private ExperienceType experienceType;

}
