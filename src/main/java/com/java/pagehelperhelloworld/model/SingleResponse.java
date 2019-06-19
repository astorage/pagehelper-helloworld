package com.java.pagehelperhelloworld.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SingleResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success;
    private String code = "00000000";
    private String message = "成功";
    private T data;

    public SingleResponse() {
    }

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> singleResponse = new SingleResponse();
        singleResponse.setSuccess(true);
        singleResponse.setData(data);
        return singleResponse;
    }


    public static SingleResponse buildFailure(String errCode, String errMessage) {
        SingleResponse response = new SingleResponse();
        response.setSuccess(false);
        response.setCode(errCode);
        response.setMessage(errMessage);
        return response;
    }

    public static SingleResponse buildSuccess() {
        SingleResponse response = new SingleResponse();
        response.setSuccess(true);
        return response;
    }
}
