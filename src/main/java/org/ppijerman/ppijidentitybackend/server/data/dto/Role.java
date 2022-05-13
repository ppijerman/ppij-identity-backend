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
@Table(name = "\"Role\"", schema = "CENSUS")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID roleId;

    @Column(name = "role_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String roleName;

    @ManyToMany
    @JoinTable(
            name = "\"Privilege_Role_Map\"",
            schema = "CENSUS",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    @ToString.Exclude
    private List<Privilege> rolePrivilege;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return roleId != null && Objects.equals(roleId, role.roleId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
