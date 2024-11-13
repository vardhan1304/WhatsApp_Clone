package com.whatsapp.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.util.Date;

@Service
public class TokenProvider {


    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication authentication){

        return Jwts.builder().setIssuer("Vardhan")
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",authentication.getName())
                .signWith(key)
                .compact();
    }

    public String getEmailFromToken(String  jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("email"));

    }
}
