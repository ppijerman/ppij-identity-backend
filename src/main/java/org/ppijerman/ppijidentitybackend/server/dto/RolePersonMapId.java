package org.ppijerman.ppijidentitybackend.server.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class RolePersonMapId implements Serializable {
    @Column(name = "role_id", columnDefinition = "UUID", nullable = false)
    private UUID roleId;
    @Column(name = "person_id", columnDefinition = "UUID", nullable = false)
    private UUID personId;
}
