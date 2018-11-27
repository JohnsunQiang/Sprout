package com.lzq.sprout.model.bean;

import java.io.Serializable;

public class BaseBean<T> implements Serializable {
    private int code;
    private String msg;
    private T results;
    private boolean error;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return results;
    }

    public void setData(T data) {
        this.results = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
