package com.netcracker.edu.fapi.models;

public enum UserRole{
    MEN("1", "admin"),
    WOMEN("2", "user");

    UserRole() {
    }

    @Override
    public String toString() {
        return "UserRole{}";
    }

    private String genderDescription;
    private String genderId;

    public String getRoleDescription() {
        return genderDescription;
    }

    public void setRoleDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    public String getRoleId() {
        return genderId;
    }

    public void setRoleId(String genderId) {
        this.genderId = genderId;
    }

    UserRole(String genderDescription, String genderId) {
        this.genderDescription = genderDescription;
        this.genderId = genderId;
    }
}

