package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Role", schema = "CENSUS")
public class Role {
    @Id
    @Column(name = "role_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID roleId;

    @Column(name = "role_name", columnDefinition = "VARCHAR(50)", length = 20, nullable = false)
    private String roleName;

    @ManyToMany
    @JoinTable(
            name = "Privilege_Role_Map",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private List<Privilege> rolePrivilege;
}
