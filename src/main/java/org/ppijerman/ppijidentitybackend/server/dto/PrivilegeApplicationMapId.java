package org.ppijerman.ppijidentitybackend.server.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class PrivilegeApplicationMapId implements Serializable {
    @Column(name = "privilege_id", columnDefinition = "UUID", nullable = false)
    private UUID privilegeId;
    @Column(name = "application_id", columnDefinition = "UUID")
    private UUID applicationId;
}
