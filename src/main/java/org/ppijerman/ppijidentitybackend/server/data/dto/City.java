package org.ppijerman.ppijidentitybackend.server.data.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"City\"", schema = "CENSUS")
public class City {
    @Id
    @GeneratedValue
    @Column(name = "city_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID cityId;

    @Column(name = "city_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String cityName;

    @OneToMany(mappedBy = "personCity")
    @ToString.Exclude
    private List<Person> residents;

    @OneToMany(mappedBy = "institutionCity")
    @ToString.Exclude
    private List<Institution> institutions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        City city = (City) o;
        return cityId != null && Objects.equals(cityId, city.cityId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
