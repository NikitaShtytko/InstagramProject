package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    private Long id;
    private String photo;
    private String txt;
    private Timestamp date;
    private String place;
    private User user;
    private List<Comment> comment;
    private List<Tag> tags;
    private Set<User> likes;
}
