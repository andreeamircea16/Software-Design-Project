package com.store.app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uuid")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    private UUID uuid;

    @Column
    private String firstName;

    @Column
    private String lastName;

    // Description no longer than 200 characters
    @Column(length = 200)
    private String description;

    @Column
    private String avatar;

    @OneToMany(mappedBy = "author")
    List<Post> posts;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "authors_users",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> subscribedUsers;
}
