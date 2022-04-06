package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name="CITY", schema="CENSUS")
public class City {
    @Id
    @Column(name="city_id", columnDefinition = "uuid")
    private UUID cityID;

    @Column(name="city_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String cityName;
}
