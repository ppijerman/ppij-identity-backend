package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Person\"", schema = "CENSUS")
public class Person {

    private static final String SYM_PASSWORD = System.getenv("SYM_PASSWORD");

    @Id
    @Column(name = "person_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID personId;

    @Column(name = "person_name", columnDefinition = "BYTEA", nullable = false)
    @ColumnTransformer(
            read = "CAST(pgp_sym_decrypt(person_name, current_setting('sym_password')) AS TEXT)",
            write = "pgp_sym_encrypt(CAST(? AS TEXT), current_setting('sym_password'), 'cipher-algo=aes256')"
    )
    private String personName;

    @Column(name = "person_birth_date", columnDefinition = "BYTEA", nullable = false)
    @ColumnTransformer(
            read = "CAST(pgp_sym_decrypt(person_birth_date, current_setting('sym_password')) AS DATE)",
            write = "pgp_sym_encrypt(CAST(? AS TEXT), current_setting('sym_password'), 'cipher-algo=aes256')"
    )
    @Temporal(TemporalType.DATE)
    private Calendar personBirthDate;

    @Column(name = "person_birth_place", columnDefinition = "BYTEA", nullable = false)
    @ColumnTransformer(
            read = "CAST(pgp_sym_decrypt(person_birth_place, current_setting('sym_password')) AS TEXT)",
            write = "pgp_sym_encrypt(CAST(? AS TEXT), current_setting('sym_password'), 'cipher-algo=aes256')"
    )
    private String personBirthPlace;

    @Column(name = "person_phone", columnDefinition = "BYTEA", nullable = false)
    @ColumnTransformer(
            read = "CAST(pgp_sym_decrypt(person_phone, current_setting('sym_password')) AS TEXT)",
            write = "pgp_sym_encrypt(CAST(? AS TEXT), current_setting('sym_password'), 'cipher-algo=aes256')"
    )
    private String personPhone;

    @Column(name = "person_email", columnDefinition = "BYTEA", nullable = false)
    @ColumnTransformer(
            read = "CAST(pgp_sym_decrypt(person_email, current_setting('sym_password')) AS TEXT)",
            write = "pgp_sym_encrypt(CAST(? AS TEXT), current_setting('sym_password'), 'cipher-algo=aes256')"
    )
    private String personEmail;

    @Column(name = "person_password", columnDefinition = "TEXT", nullable = false)
    @ColumnTransformer(
            write = "crypt(?, gen_salt('bf'))"
    )
    private String personPassword;

    @Column(name = "person_uni_email", columnDefinition = "BYTEA")
    @ColumnTransformer(
            read = "CAST(pgp_sym_decrypt(person_uni_email, current_setting('sym_password')) AS TEXT)",
            write = "pgp_sym_encrypt(CAST(? AS TEXT), current_setting('sym_password'), 'cipher-algo=aes256')"
    )
    private String personUniEmail;

    // We don't encrypt this to prevent dictionary attack
    @Column(name = "person_status", columnDefinition = "VARCHAR(30)", length = 30)
    private String personStatus;

    // We don't encrypt this to prevent dictionary attack
    @Column(name = "person_is_email_verified", columnDefinition = "boolean", nullable = false)
    private boolean personIsEmailVerified;

    @Column(name = "person_last_student_status_verified", columnDefinition = "BYTEA", nullable = false)
    @ColumnTransformer(
            read = "CAST(pgp_sym_decrypt(person_last_student_status_verified, current_setting('sym_password')) AS DATE)",
            write = "pgp_sym_encrypt(CAST(? AS TEXT), current_setting('sym_password'), 'cipher-algo=aes256')"
    )
    @Temporal(TemporalType.DATE)
    private Calendar personLastVerified;

    @Column(name = "person_zipcode", columnDefinition = "BYTEA", nullable = false)
    @ColumnTransformer(
            read = "CAST(pgp_sym_decrypt(person_zipcode, current_setting('sym_password')) AS TEXT)",
            write = "pgp_sym_encrypt(CAST(? AS TEXT), current_setting('sym_password'), 'cipher-algo=aes256')"
    )
    private String personZipcode;

    @Column(name = "person_street", columnDefinition = "BYTEA", nullable = false)
    @ColumnTransformer(
            read = "CAST(pgp_sym_decrypt(person_street, current_setting('sym_password')) AS TEXT)",
            write = "pgp_sym_encrypt(CAST(? AS TEXT), current_setting('sym_password'), 'cipher-algo=aes256')"
    )
    private String personStreet;

    @Column(name = "person_street_number", columnDefinition = "BYTEA", nullable = false)
    @ColumnTransformer(
            read = "CAST(pgp_sym_decrypt(person_street_number, current_setting('sym_password')) AS TEXT)",
            write = "pgp_sym_encrypt(CAST(? AS TEXT), current_setting('sym_password'), 'cipher-algo=aes256')"
    )
    private String personStreetNumber;

    @ManyToOne()
    @JoinColumn(name = "person_branch_id", columnDefinition = "uuid")
    private Branch personBranch;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_city_id", columnDefinition = "uuid")
    private City personCity;

    @ManyToMany
    @JoinTable(
            name = "\"Role_Person_Map\"",
            schema = "CENSUS",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> personRole;
}
