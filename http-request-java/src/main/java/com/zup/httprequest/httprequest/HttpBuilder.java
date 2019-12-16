package com.zup.httprequest.httprequest;

import okhttp3.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Primary
@Component
public class HttpBuilder {

    private final OkHttpClient httpClient = new OkHttpClient();

    private static String key = "PROJECT_ZUP";

    private final JWTBuilder jwtBuilder;

    public HttpBuilder(JWTBuilder jwtBuilder) { this.jwtBuilder = jwtBuilder; }


    public String post() throws IOException{
        FormBody formBody = new FormBody.Builder()
                .add("id", "123")
                .add("field", "testing")
                .build();

        Request request = new Request.Builder()
                .url("https://httpbin.org/post")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String delete() throws IOException{
        Request request = new Request.Builder()
                .url("https://httpbin.org/delete")
                .delete()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String auth() throws IOException{

        String login = "login";
        String password = "login123";
        String secret = jwtBuilder.createJWT(login + password, key);

        Request request = new Request.Builder()
                .url("https://httpbin.org/bearer")
                .header("Authorization", "Bearer " + secret)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
