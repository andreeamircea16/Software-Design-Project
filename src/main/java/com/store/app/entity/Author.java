package com.store.app.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    private UUID uuid;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String description;

    @Column
    private String avatar;
}
