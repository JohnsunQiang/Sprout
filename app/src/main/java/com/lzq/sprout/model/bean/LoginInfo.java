package com.lzq.sprout.model.bean;

public class LoginInfo extends BaseBean {
    private String token;
    private String type;
    private String is_allow_create;

    public String getIs_allow_create() {
        return is_allow_create;
    }

    public void setIs_allow_create(String is_allow_create) {
        this.is_allow_create = is_allow_create;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", is_allow_create='" + is_allow_create + '\'' +
                '}';
    }
}
