package com.zup.httprequest.httprequest;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpService {

    private final HttpBuilder httpBuilder;

    public HttpService(HttpBuilder httpBuilder) { this.httpBuilder = httpBuilder; }

    public String sendPostRequest(){
        try{
            return httpBuilder.post();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String sendDeleteRequest(){
        try{
            return httpBuilder.delete();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String sendBearerAuthorization(){
        try{
            return httpBuilder.auth();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
