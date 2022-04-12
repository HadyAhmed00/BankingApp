package com.example.bankingapp;

public class User {
    int id;

    public User(int id, String name, String phone_number, String emile, int photo, float balace) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.emile = emile;
        this.photo = photo;
        this.balace = balace;
    }

    private String  name,
                    phone_number,
                    emile;
    int photo;

    float balace;

    public User(String name, String phone_number, String emile, int photo, float balace) {
        this.name = name;
        this.phone_number = phone_number;
        this.emile = emile;
        this.photo = photo;
        this.balace = balace;
    }

    public User(String name, String phone_number, String emile, float balace) {
        this.name = name;
        this.phone_number = phone_number;
        this.emile = emile;
        this.balace = balace;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmile() {
        return emile;
    }

    public float getBalace() {
        return balace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmile(String emile) {
        this.emile = emile;
    }

    public void setBalace(float balace) {
        this.balace = balace;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
