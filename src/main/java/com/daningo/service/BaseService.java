package com.daningo.service;

import com.daningo.entity.ResponseContent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.data.Header;
import org.restlet.engine.header.HeaderConstants;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

public class BaseService extends ServerResource {

    Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();

    public BaseService()
    {
        super();
    }

    void setResponseHeaders()
    {
        Series<Header> responseHeaders = (Series<Header>) getResponse()
                .getAttributes().get(HeaderConstants.ATTRIBUTE_HEADERS);
        if (responseHeaders == null) {
            responseHeaders = new Series<Header>(Header.class);
        }
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        getResponse().getAttributes().put(HeaderConstants.ATTRIBUTE_HEADERS,
                responseHeaders);
        getResponse().setAccessControlAllowOrigin("*");
    }

    String getResponseJSON(Object responseData, String message){
        ResponseContent responseContent = new ResponseContent();
        responseContent.setResponseMessage(message, getRequest().getMethod());
        responseContent.setData(responseData);
        return gson.toJson(responseContent);
    }
}
