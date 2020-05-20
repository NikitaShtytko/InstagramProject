package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserRole{
    ADMIN("ADMIN"),
    USER("USER");

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    private String name;

    @JsonCreator
    public static UserRole forValues(@JsonProperty("name") String name){
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name.equals(name)){
                return userRole;
            }
        }
        return null;
    }
}