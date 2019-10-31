package com.sandu.quizz4games;

public class Users {
    private String name, image;
    private String position,e_score, t_score;

    public Users(String name, String image, String position, String e_score, String t_score) {
        this.name = name;
        this.image = image;
        this.position = position;
        this.e_score = e_score;
        this.t_score = t_score;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getE_score() {
        return e_score;
    }

    public void setE_score(String e_score) {
        this.e_score = e_score;
    }

    public String getT_score() {
        return t_score;
    }

    public void setT_score(String t_score) {
        this.t_score = t_score;
    }


}
