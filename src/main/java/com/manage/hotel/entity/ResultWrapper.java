package com.manage.hotel.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ResultWrapper implements Serializable {

    private String message;

    private Integer statusCode;

    private Object object;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * 清空参数
     */
    public void clear(){
        this.message = null;
        this.statusCode = null;
        this.object = null;
    }
}
