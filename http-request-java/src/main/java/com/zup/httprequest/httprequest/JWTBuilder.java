package com.zup.httprequest.httprequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JWTBuilder {

    public static String createJWT(String subject, String key) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        String jwt =  Jwts.builder().setId(UUID.randomUUID().toString())
                .setSubject(subject)
                .signWith(signatureAlgorithm, key)
                .compact();

        return jwt;
    }

}
