package com.yichuangzhihui.robotvrp.util;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wangqingwei
 * @date 2020-09-11
 */
@NoArgsConstructor
public class Result<T> implements Serializable {
    private T data;
    private String message;
    private String code;


    public Result(T data, String message, String code) {
        this.setData((data == null) ? (T) new Object() : data);
        this.setMessage(message);
        this.setCode(code);

    }
    public Result(String message, String code) {
        this.setMessage(message);
        this.setCode(code);
    }

    public Result<T> setErrorMsg(String code, String errorMsg) {
        this.code = code;
        this.message = errorMsg;
        return this;

    }

    public Result<T> code(String code) {
        this.setCode(code);
        return this;
    }

    public Result<T> data(T data) {
        this.setData((data == null) ? (T) new Object() : data);
        return this;
    }

    public Result<T> msg(String message) {
        this.setMessage(message);
        return this;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
