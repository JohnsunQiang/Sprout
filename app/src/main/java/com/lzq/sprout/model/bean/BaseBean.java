package com.lzq.sprout.model.bean;

import java.io.Serializable;

public class BaseBean<T> implements Serializable {
    private String result;
    private String msg;
    private T data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
