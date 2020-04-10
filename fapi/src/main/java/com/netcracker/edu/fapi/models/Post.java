package com.netcracker.edu.fapi.models;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    private Long id;
    private Long photo;
    private String txt;
    private String date;
    private String place;

    public Post(){
    }

    public Post(Long id, Long photo, String txt, String date, String place) {
        this.id = id;
        this.photo = photo;
        this.txt = txt;
        this.date = date;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhoto() {
        return photo;
    }

    public void setPhoto(Long photo) {
        this.photo = photo;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
