package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Institution\"", schema = "CENSUS")
public class Institution {
    @Id
    @Column(name = "institution_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID institutionId;

    @Column(name = "institution_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String institutionName;

    @Column(name = "institution_phone", columnDefinition = "VARCHAR(20)", length = 20)
    private String institutionPhone;

    @Column(name = "institution_email", columnDefinition = "VARCHAR(100)", length = 100)
    private String institutionEmail;

    @Column(name = "institution_zipcode", columnDefinition = "VARCHAR(10)", length = 10)
    private String institutionZipcode;

    @Column(name = "institution_street", columnDefinition = "VARCHAR(50)", length = 50)
    private String institutionStreet;

    @Column(name = "institution_street_number", columnDefinition = "VARCHAR(5)", length = 5)
    private String institutionStreetNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "institution_city_id", nullable = false)
    private City institutionCity;
}
