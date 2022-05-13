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
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "\"Category\"", schema = "CENSUS")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id", columnDefinition = "UUID default uuid_generate_v4()", updatable = false)
    private UUID categoryId;

    @Column(name = "category_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "experienceCategory")
    @ToString.Exclude
    private List<Experience> experiences;

    @OneToMany(mappedBy = "skillCategory")
    @ToString.Exclude
    private List<Skill> skills;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return categoryId != null && Objects.equals(categoryId, category.categoryId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
