package com.network;

/**
 * Created by luo.j on 2019/7/4.
 */

public class BaseBean {
  private   String code;
    private String response;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
                "code='" + code + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
