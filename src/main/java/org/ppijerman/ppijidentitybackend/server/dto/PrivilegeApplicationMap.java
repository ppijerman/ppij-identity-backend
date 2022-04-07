package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="PRIVILEGE_APPLICATION_MAP", schema="CENSUS")
public class PrivilegeApplicationMap {
    @EmbeddedId
    private PrivilegeApplicationMapId id;


    @ManyToMany
    @JoinColumn(name="privilege_id")
    private List<Privilege> privilege;

    @ManyToMany
    @JoinColumn(name="application_id")
    private List<Application> application;
}
