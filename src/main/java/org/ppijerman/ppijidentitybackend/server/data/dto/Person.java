package org.ppijerman.ppijidentitybackend.server.data.dto;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnTransformer;
import org.ppijerman.ppijidentitybackend.server.service.security.crypto.database.CalendarEncryptor;
import org.ppijerman.ppijidentitybackend.server.service.security.crypto.database.DateEncryptor;
import org.ppijerman.ppijidentitybackend.server.service.security.crypto.database.StringEncryptor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "\"Person\"", schema = "CENSUS")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "person_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID personId;

    @Column(name = "person_name", columnDefinition = "BYTEA", nullable = false)
    @Convert(converter = StringEncryptor.class)
    @Setter
    private String personName;

    @Column(name = "person_birth_date", columnDefinition = "BYTEA", nullable = false)
    @Convert(converter = DateEncryptor.class)
    @Temporal(TemporalType.DATE)
    @Setter
    private Date personBirthDate;

    @Column(name = "person_birth_place", columnDefinition = "BYTEA", nullable = false)
    @Convert(converter = StringEncryptor.class)
    @Setter
    private String personBirthPlace;

    @Column(name = "person_phone", columnDefinition = "BYTEA", nullable = false)
    @Convert(converter = StringEncryptor.class)
    @Setter
    private String personPhone;

    @Column(name = "person_email", columnDefinition = "BYTEA", nullable = false)
    @Convert(converter = StringEncryptor.class)
    @Setter
    private String personEmail;

    @Column(name = "person_password", columnDefinition = "TEXT", nullable = false)
    @ColumnTransformer(
            write = "crypt(?, gen_salt('bf'))"
    )
    @Setter
    private String personPassword;

    @Column(name = "person_signup_timestamp", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar personSignupTimestamp;

    @Column(name = "person_uni_email", columnDefinition = "BYTEA")
    @Convert(converter = StringEncryptor.class)
    @Setter
    private String personUniEmail;

    // We don't encrypt this to prevent dictionary attack
    @Column(name = "person_status", columnDefinition = "VARCHAR(30)", length = 30)
    @Setter
    private String personStatus;

    // We don't encrypt this to prevent dictionary attack
    @Column(name = "person_is_email_verified", columnDefinition = "boolean", nullable = false)
    @Setter
    private boolean personIsEmailVerified;

    @Column(name = "person_last_student_status_verified", columnDefinition = "BYTEA")
    @Convert(converter = CalendarEncryptor.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter
    private Calendar personLastVerified;

    @Column(name = "person_zipcode", columnDefinition = "BYTEA", nullable = false)
    @Convert(converter = StringEncryptor.class)
    @Setter
    private String personZipcode;

    @Column(name = "person_street", columnDefinition = "BYTEA", nullable = false)
    @Convert(converter = StringEncryptor.class)
    @Setter
    private String personStreet;

    @Column(name = "person_street_number", columnDefinition = "BYTEA", nullable = false)
    @Convert(converter = StringEncryptor.class)
    @Setter
    private String personStreetNumber;

    @ManyToOne
    @JoinColumn(name = "person_branch_id", columnDefinition = "uuid")
    private Branch personBranch;

    @ManyToOne
    @JoinColumn(name = "person_city_id", columnDefinition = "UUID")
    private City personCity;

    @ManyToMany
    @JoinTable(
            name = "\"Role_Person_Map\"",
            schema = "CENSUS",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ToString.Exclude
    @Setter
    private List<Role> personRole;

    @OneToMany(mappedBy = "educationPerson", orphanRemoval = true)
    @ToString.Exclude
    @Setter
    private List<Education> educations;

    @OneToMany(mappedBy = "applicationOwner", orphanRemoval = true)
    @ToString.Exclude
    @Setter
    private List<Application> applications;

    @OneToMany(mappedBy = "experiencePerson", orphanRemoval = true)
    @ToString.Exclude
    @Setter
    private List<Experience> experiences;

    @OneToMany(mappedBy = "skillPerson", orphanRemoval = true)
    @ToString.Exclude
    @Setter
    private List<Skill> skills;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return personId != null && Objects.equals(personId, person.personId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}