package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Category\"", schema = "CENSUS")
public class Category {
    @Id
    @Column(name = "category_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID categoryId;

    @Column(name = "category_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "experienceCategory")
    private List<Experience> experiences;

    @OneToMany(mappedBy = "skillCategory")
    private List<Skill> skills;
}
