package com.store.app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"date", "modified"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uuid")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    private UUID uuid;

    @Column(unique=true)
    private String slug;

//    @CreatedDate
    // defaulting to current time when it was inserted into the database
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    @CreatedDate
    private Date date;

//    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
//    @CreatedDate
    private Date modified;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    // Description no longer than 200 characters
    @Column(length = 200)
    private String contentDescription;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany()
    @JoinTable(
            name = "posts_categories",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany()
    @JoinTable(
            name = "posts_comments",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private List<Comment> comments;

    @Column
    private String thumbnail;
}
