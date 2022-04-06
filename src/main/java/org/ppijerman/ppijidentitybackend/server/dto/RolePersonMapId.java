package org.ppijerman.ppijidentitybackend.server.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class RolePersonMapId implements Serializable {
    @Column(name = "role_id")
    private UUID roleId;
    @Column(name = "person_id")
    private UUID personId;
}
