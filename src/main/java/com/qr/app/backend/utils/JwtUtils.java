package com.qr.app.backend.utils;


import java.security.Key;
import java.util.Date;

import com.qr.app.backend.impl.UserDetailsImplement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
public class JwtUtils {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtUtils.class);

    @Value("{server.JWT-SECRET}")
    private String jwtSecretKey;
    @Value ("{server.JWT-EXPIRATION")
    private long jwtExpiration;


    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));

    }
    public String generateJwtToken(Authentication auth){
        UserDetailsImplement userDetails = new UserDetailsImplement();
        Date now = new Date();
        Date expiriationDate = new Date(now.getTime() + jwtExpiration);
        String token = Jwts.builder()
                .claim("name", userDetails.getUsername())
                .claim("email", userDetails.getUsername())
                .setSubject((userDetails.getEmail()))
                .setIssuedAt(now)
                .setExpiration(expiriationDate)
                .signWith(key(),SignatureAlgorithm.HS256)
                .compact();
        return token;
    }
    public String getUserFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    public String validateToken(String authToken){
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return "Token is correct";

        }catch(MalformedJwtException e){
            return "Invalid JWT token: {}" + e.getMessage();
        } catch(ExpiredJwtException e){
            return "Jwt token is expired: {}" + e.getMessage();
        } catch (UnsupportedJwtException e){
            return "JWT token is not supported: {}" + e.getMessage();
        } catch(IllegalArgumentException e){
            return "JWT claims string is empty: {}" + e.getMessage();
        }
    }
}
