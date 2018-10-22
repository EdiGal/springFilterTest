package com.upright;

public class ConnectInfo {
    private int userId;
    private String userToken;

    public ConnectInfo(int userId, String userToken) {
        this.userId = userId;
        this.userToken = userToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
