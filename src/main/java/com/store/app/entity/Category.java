package com.store.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    private UUID uuid;

    @Column
    private String name;

    // Description no longer than 300 characters
    @Column(length = 300)
    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;
}
