package com.daningo.com.daningo.com.daningo.entity;

import org.restlet.data.Method;

public class ResponseContent {
    private String responseMessage;
    private Object data;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage, Method method) {
        if(method == Method.PUT)
        {
            this.responseMessage = responseMessage + " updated successfully.";
        } else if(method == Method.POST){
            this.responseMessage = responseMessage + " created successfully.";
        }else {
            this.responseMessage = responseMessage;
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
