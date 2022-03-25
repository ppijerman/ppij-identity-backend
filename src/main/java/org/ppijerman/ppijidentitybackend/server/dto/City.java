package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="CITY", schema="CENSUS")
public class City {
    //    TODO: Generated Value or use setter?
    @Id
    private int city_id;

    @Column(length = 50, nullable=false)
    private String city_name;
}
