package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Table(name = "\"Privilege\"", schema = "CENSUS")
public class Privilege {
    @Id
    @Column(name = "privilege_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID privilegeId;

    @Column(name = "privilege_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String privilegeName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Privilege privilege = (Privilege) o;
        return privilegeId != null && Objects.equals(privilegeId, privilege.privilegeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
