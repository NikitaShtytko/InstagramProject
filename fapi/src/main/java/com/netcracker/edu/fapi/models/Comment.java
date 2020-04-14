package com.netcracker.edu.fapi.models;

import lombok.Data;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    private Long id;
    private String txt;
    private User user;
}

