package me.cxis.activity.api.model.common;

import java.io.Serializable;

public class Result <T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public Result() {
    }

    public static <T> Result success(T data) {
        Result<T> result = new Result();
        result.setData(data);
        return result;
    }

    public static <T> Result fail(int code, String msg) {
        Result<T> result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
