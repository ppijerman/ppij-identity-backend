package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="DEGREE", schema="CENSUS")
public class Degree {
    // TODO: Generated Value or use setter?
    @Id
    private int degree_id;

    @Column(length = 50, nullable=false)
    private String degree;
}
