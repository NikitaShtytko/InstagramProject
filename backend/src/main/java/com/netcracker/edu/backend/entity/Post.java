package com.netcracker.edu.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private Long photo;

    private String txt;

    private Timestamp date;

    private String place;

    //private String tag;

//    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
         @JoinTable(
                 name = "likee",
                 joinColumns = @JoinColumn(name = "post_id"),
                 inverseJoinColumns = @JoinColumn(name = "user_id")
         )
    Set<User> likes;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comment;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "tags_has_posts",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();
}
