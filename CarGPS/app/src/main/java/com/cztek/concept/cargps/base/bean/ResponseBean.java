package com.cztek.concept.cargps.base.bean;

/**
 * Created by HH
 * Date: 2017/12/4
 */

public class ResponseBean {
    private Integer code;
    private String message;
    private Boolean result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
