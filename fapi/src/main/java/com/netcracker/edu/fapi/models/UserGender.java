package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserGender{
    MEN("MAN"),
    WOMEN("WOMEN");

    UserGender(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

//    @JsonCreator
//    public static UserGender forValue(String name)
//    {
//        return EnumUtil.getEnumByNameIgnoreCase(UserGender.class, name);
//    }

    private String name;

    @JsonCreator
    public static UserGender forValues(@JsonProperty("name") String name){
        for (UserGender userGender : UserGender.values()) {
            if (userGender.name.equals(name)){
                return userGender;
            }
        }
        return null;
    }
}

