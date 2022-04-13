package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Data
@Table(name="EXPERIENCE", schema="CENSUS")
public class Experience {
    @Id
    @Column(name="experience_id", columnDefinition = "UUID")
    private UUID experienceId;

    @Column(name="experience_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String experienceName;

    @Column(name="is_international", columnDefinition="BOOLEAN", nullable=false)
    private boolean isInternational;

    @Column(name="start_date", columnDefinition = "DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column(name="end_date", columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private Calendar endDate;


    @ManyToOne
    @JoinColumn(name = "person_id", columnDefinition = "UUID", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "category_id", columnDefinition = "INTEGER", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "type_id", columnDefinition = "UUID", nullable = false)
    private ExperienceType experienceType;

}
