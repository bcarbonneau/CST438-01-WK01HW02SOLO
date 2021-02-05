package com.example.cst438_01_wk01hw02solo;

public class User {

    private String username;
    private String password;
    private int uId;

    public User(String username, String password, int uId) {
        this.username = username;
        this.password = password;
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getuId() {
        return uId;
    }
}

