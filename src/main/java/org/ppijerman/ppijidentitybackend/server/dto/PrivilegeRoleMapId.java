package org.ppijerman.ppijidentitybackend.server.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class PrivilegeRoleMapId implements Serializable {
    @Column(name = "role_id", columnDefinition = "UUID", nullable = false)
    private UUID roleId;
    @Column(name = "privilege_id", columnDefinition = "UUID", nullable = false)
    private UUID privilegeId;
}
