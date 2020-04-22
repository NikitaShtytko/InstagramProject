package com.netcracker.edu.fapi.models;

public enum UserGender{
    MEN("1", "male"),
    WOMEN("2", "female");

    UserGender() {
    }

    @Override
    public String toString() {
        return "UserGender{}";
    }

    private String genderDescription;
    private String genderId;

    public String getGenderDescription() {
        return genderDescription;
    }

    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    UserGender(String genderDescription, String genderId) {
        this.genderDescription = genderDescription;
        this.genderId = genderId;
    }
}

