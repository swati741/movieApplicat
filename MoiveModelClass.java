package com.example.movieapp;

public class MoiveModelClass {

    String id;
    String name;
    String image;
//    String overview;

    public MoiveModelClass(String id, String name, String image, String overview)
    {
        this.id = id;
        this.name = name;
        this.image = image;
//        this.overview= overview;
    }

    public MoiveModelClass()
    {

    }

    public String getId() {
        return id;
    }

//    public String getOverview() {
//        return overview;
//    }

//    public void setOverview(String overview) {
//        this.overview = overview;
//    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
