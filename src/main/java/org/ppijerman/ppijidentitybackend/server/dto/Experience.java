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
    @Column(name="experience_id", columnDefinition = "uuid")
    private UUID experienceID;

    @Column(name="experience_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String experienceName;

    @Column(name="is_international", columnDefinition="boolean", nullable=false)
    private String isInternational;

    @Column(name="start_date")
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column(name="end_date")
    @Temporal(TemporalType.DATE)
    private Calendar endDate;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ExperienceType experienceType;

}
