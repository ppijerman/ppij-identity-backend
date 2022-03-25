package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Data
@Table(name="PERSON", schema="CENSUS")
public class Person {

//    TODO: create Persistence.xml?

//    TODO: Generated Value or use setter?
    @Id
    private int person_id;

    @Column(length = 50, nullable=false)
    private String name;

    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private Calendar birth_date;

    @Column(length = 20, nullable=false)
    private String birth_place;

    @Column(length = 20, nullable=false)
    private String phone;

    @Column(length = 50, nullable=false)
    private String email;

    @Column(length = 50, nullable=false)
    private String password;

    @Column(length = 50)
    private String uni_email;

    private boolean status;

    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private Calendar last_verified;

    @Column(length = 10, nullable=false)
    private String zipcode;

    @Column(length = 50, nullable=false)
    private String street;

    @Column(columnDefinition="CHAR(4)", nullable=false)
    private String street_number;



    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch_id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city_id;

    @OneToMany(mappedBy = "education_id")
    private List<Education> education_ids = new ArrayList<>();

}
