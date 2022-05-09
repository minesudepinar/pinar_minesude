package com.example.userloginsystem;

public class User {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private boolean isInfoAdded = false;

    User(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setInfoAdded(boolean infoAdded) {
        isInfoAdded = infoAdded;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isInfoAdded() {
        return isInfoAdded;
    }
}
