package com.example.tourmate.model;

public class User {
    private int id;
    private String name, email, phone, password, address, image;

    public User() {

    }

    public User(int id, String name, String email, String phone, String password, String address, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }
}
