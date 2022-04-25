package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"City\"", schema = "CENSUS")
public class City {
    @Id
    @Column(name = "city_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID cityId;

    @Column(name = "city_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String cityName;

    @OneToMany(mappedBy = "personCity")
    private List<Person> residents;

    @OneToMany(mappedBy = "institutionCity")
    private List<Institution> institutions;
}
