package com.netcracker.edu.fapi.models;

public enum UserRole{
    ADMIN("1", "admin"),
    USER("2", "user");

    UserRole() {
    }

    @Override
    public String toString() {
        return "UserRole{}";
    }

    private String roleDescription;
    private String roleId;

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String genderId) {
        this.roleId = genderId;
    }

    UserRole(String roleDescription, String roleId) {
        this.roleDescription = roleDescription;
        this.roleId = roleId;
    }
}

