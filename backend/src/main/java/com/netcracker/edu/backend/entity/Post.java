package com.netcracker.edu.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private Long photo;

    private String txt;

    private String date;

    private String place;

    private String tag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany
         @JoinTable(
                 name = "likee",
                 joinColumns = @JoinColumn(name = "post_id"),
                 inverseJoinColumns = @JoinColumn(name = "user_id")
         )
    Set<User> likes;


//    @OneToMany
//    @JoinColumn(name = "post_id")
//    private List<Like> like;
//
//    @OneToMany
//    @JoinColumn(name = "post_id")
//    private List<Comment> comment;
//
//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinTable(name = "tags_has_posts",
//            joinColumns = @JoinColumn(name = "post_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
//    private List<Tag> tags = new ArrayList<>();
}
