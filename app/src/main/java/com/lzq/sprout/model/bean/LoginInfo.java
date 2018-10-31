package com.lzq.sprout.model.bean;

public class LoginInfo extends BaseBean {
    private String token;
    private String token_type;
    private long expires;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return token_type;
    }

    public void setType(String type) {
        this.token_type = type;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "token='" + token + '\'' +
                ", type='" + token_type + '\'' +
                ", expires='" + expires + '\'' +
                '}';
    }
}
