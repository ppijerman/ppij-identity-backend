package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Person", schema = "CENSUS")
public class Person {
    @Id
    @Column(name = "person_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID personId;

    @Column(name = "person_name", columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
    private String personName;

    @Column(name = "person_birth_date", columnDefinition = "DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar personBirthDate;

    @Column(name = "person_birth_place", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String personBirthPlace;

    @Column(name = "person_phone", columnDefinition = "VARCHAR(20)", length = 20, nullable = false)
    private String personPhone;

    @Column(name = "person_email", columnDefinition = "VARCHAR(100)", length = 100, nullable = false)
    private String personEmail;

    @Column(name = "person_password", columnDefinition = "VARCHAR(60)", length = 60, nullable = false)
    private String personPassword;

    @Column(name = "person_uni_email", columnDefinition = "VARCHAR(100)", length = 100)
    private String personUniEmail;

    @Column(name = "person_status", columnDefinition = "VARCHAR(30)", length = 30)
    private String personStatus;

    @Column(name = "person_is_email_verified", columnDefinition = "boolean", nullable = false)
    private boolean personIsEmailVerified;

    @Column(name = "person_last_student_status_verified", columnDefinition = "DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar personLastVerified;

    @Column(name = "person_zipcode", columnDefinition = "VARCHAR(10)", length = 10, nullable = false)
    private String personZipcode;

    @Column(name = "person_street", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String personStreet;

    @Column(name = "person_street_number", columnDefinition = "VARCHAR(5)", length = 5, nullable = false)
    private String personStreetNumber;

    @ManyToOne()
    @JoinColumn(name = "person_branch_id", columnDefinition = "uuid")
    private Branch personBranch;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_city_id", columnDefinition = "uuid", nullable = false)
    private City personCity;

    @ManyToMany
    @JoinTable(
            name = "Role_Person_Map",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> personRole;
}
