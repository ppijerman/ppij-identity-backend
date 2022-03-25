package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
@Table(name="INSTITUTE", schema="CENSUS")
public class Institute {
    //    TODO: Generated Value or use setter?
    @Id
    private int institute_id;

    @Column(length = 50, nullable=false)
    private String institute_name;

    @Column(length = 20)
    private String phone;

    @Column(length = 50)
    private String email;

    @Column(length = 10)
    private String zipcode;

    @Column(length = 50)
    private String street;

    @Column(columnDefinition="CHAR(5)")
    private String street_number;
}
