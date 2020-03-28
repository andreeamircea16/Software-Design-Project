package com.store.app.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    private UUID uuid;

    @Column()
    private String slug;

    @Column()
    private String date;

    @Column()
    private String modified;

    @Column()
    private String title;

    @Column()
    private String content;

    @Column()
    private String contentDescription;

    @Column()
    private String author;

    @Column()
    private String thumbnail;
}
