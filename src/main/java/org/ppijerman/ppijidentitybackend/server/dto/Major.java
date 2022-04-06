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
    @Column(name="major_id", columnDefinition = "uuid")
    private UUID majorID;

    @Column(name="major_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String majorName;
}
