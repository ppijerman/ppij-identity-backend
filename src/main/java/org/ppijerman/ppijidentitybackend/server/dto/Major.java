package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name="MAJOR", schema="CENSUS")
public class Major {
    @Id
    @Column(name="major_id", columnDefinition = "UUID")
    private UUID majorId;

    @Column(name="major_name", columnDefinition="VARCHAR(40)", length = 40, nullable=false)
    private String majorName;
}
