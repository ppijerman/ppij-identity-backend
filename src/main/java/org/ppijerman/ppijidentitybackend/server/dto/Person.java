package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="PERSON", schema="CENSUS")
public class Person {

    @Id
    @Column(name="person_id", columnDefinition = "UUID")
    private UUID personId;

    @Column(name="name", columnDefinition="VARCHAR(100)", length = 100, nullable=false)
    private String name;

    @Column(name="birth_date", columnDefinition="DATE", nullable=false)
    @Temporal(TemporalType.DATE)
    private Calendar birthDate;

    @Column(name="birth_place", columnDefinition="VARCHAR(20)", length = 20, nullable=false)
    private String birthPlace;

    @Column(name="phone", columnDefinition="VARCHAR(20)", length = 20, nullable=false)
    private String phone;

    @Column(name="email", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String email;

    @Column(name="password", columnDefinition="VARCHAR(50)", length = 50, nullable=false)
    private String password;

    @Column(name="uni_email", columnDefinition="VARCHAR(50)", length = 50)
    private String uniEmail;

    @Column(name="status", columnDefinition="boolean")
    private boolean status;

    @Column(name="last_verified", columnDefinition="DATE")
    @Temporal(TemporalType.DATE)
    private Calendar lastVerified;

    @Column(name="zipcode", columnDefinition="VARCHAR(10)", length = 10, nullable=false)
    private String zipcode;

    @Column(name="street", columnDefinition="VARCHAR(50)", length = 50)
    private String street;

    @Column(name="street_number", columnDefinition="VARCHAR(5)", length = 5)
    private String streetNumber;

    @ManyToOne
    @JoinColumn(name = "branch_id", columnDefinition = "UUID")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "city_id", columnDefinition = "UUID")
    private City city;


}
