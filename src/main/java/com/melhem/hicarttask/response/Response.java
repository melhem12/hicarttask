package com.melhem.hicarttask.response;

import java.io.Serializable;

public class Response<T> extends ResponseBase implements Serializable {
    private T data;
    public Response() {
        super();
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public boolean isSuccessful() {
        return isStatus();
    }
}