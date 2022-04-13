package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Data
@Table(name="INSTITUTE", schema="CENSUS")
public class Institute {
    @Id
    @Column(name="institute_id", columnDefinition = "UUID")
    private UUID instituteId;

    @Column(name="institute_name", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String instituteName;

    @Column(name="phone", columnDefinition="VARCHAR(20)", length = 20)
    private String phone;

    @Column(name="email", columnDefinition="VARCHAR(50)", length = 50)
    private String email;

    @Column(name="zipcode", columnDefinition="VARCHAR(10)", length = 10)
    private String zipcode;

    @Column(name="street", columnDefinition="VARCHAR(50)", length = 50)
    private String street;

    @Column(name="street_number", columnDefinition="VARCHAR(5)", length = 5)
    private String streetNumber;

    @OneToOne
    @JoinColumn(name = "city_id", columnDefinition = "UUID")
    private City city;
}
