package com.fiuady.vision.data.local;

public class User {
    private String userName;
    private String password;
    private String telNumber;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public User(String userName, String password, String telNumber) {
        this.userName = userName;
        this.password = password;
        this.telNumber = telNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
