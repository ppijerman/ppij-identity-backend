package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="ROLE_PERSON_MAP", schema="CENSUS")
public class RolePersonMap {
    @EmbeddedId
    private RolePersonMapId id;

    @ManyToMany
    @JoinColumn(name="person_id", columnDefinition = "UUID", nullable = false)
    private List<Person> person;

    @ManyToMany
    @JoinColumn(name="role_id", columnDefinition = "UUID", nullable = false)
    private List<Role> role;
}
