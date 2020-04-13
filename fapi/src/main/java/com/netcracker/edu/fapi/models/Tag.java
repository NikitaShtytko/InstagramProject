package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {

    private Long id;
    private String tag;
    private String txt;

    public Tag(){
    }

    public Tag(Long id, String tag, String txt) {
        this.id = id;
        this.tag = tag;
        this.txt = txt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }

}
