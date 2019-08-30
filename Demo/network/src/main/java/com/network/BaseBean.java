package com.network;

import java.io.Serializable;

/**
 * Created by luo.j on 2019/7/4.
 */

public class BaseBean<T> implements Serializable{

    private int code=0;
    private String msg;

    private T data;
   protected String response;
    public int a;

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
        if(data==null&&data instanceof String){
            this.data= (T) "----制怒来了----";
        }
        return data;
    }

    public void setData(T data) {
        if(data==null&&data instanceof String){
            this.data= (T) "----制怒来了----";
        }else{
            this.data = data;
        }

    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", response='" + response + '\'' +
                '}';
    }
}
