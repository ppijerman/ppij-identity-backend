package org.ppijerman.ppijidentitybackend.server.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "Category", schema = "CENSUS")
public class Category {
    @Id
    @Column(name = "category_id", columnDefinition = "uuid default uuid_generate_v4()")
    private UUID categoryId;

    @Column(name = "category_name", columnDefinition = "VARCHAR(50)", length = 50, nullable = false)
    private String categoryName;
}