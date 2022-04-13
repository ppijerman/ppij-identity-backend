package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="PRIVILEGE_ROLE_MAP", schema="CENSUS")
public class PrivilegeRoleMap {
    @EmbeddedId
    private PrivilegeRoleMapId id;


    @ManyToMany
    @JoinColumn(name="privilege_id", columnDefinition = "UUID", nullable = false)
    private List<Privilege> privilege;

    @ManyToMany
    @JoinColumn(name="role_id", columnDefinition = "UUID", nullable = false)
    private List<Role> role;
}
