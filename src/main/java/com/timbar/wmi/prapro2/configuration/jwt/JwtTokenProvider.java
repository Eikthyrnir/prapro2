package com.timbar.wmi.prapro2.configuration.jwt;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expirationInMilliseconds}")
    private Integer expirationInMilliseconds;

    @PostConstruct
    protected void init(){
//        secret = Base64.getEncoder().encodeToString(secret.getBytes());
        secret = Sha512DigestUtils.shaHex(secret);
    }

    public String createToken(UserDetails user){
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", user.getAuthorities());

        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        //UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        String username = getUsername(token);
        //TODO HOLY SHIT
        List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody().get("roles", List.class).stream()
                .map(t -> ((LinkedHashMap<String, String>) t).entrySet())
                .flatMap(t1 -> ((Set) t1).stream())
                .map(t2 -> ((Map.Entry) t2).getValue())
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
//        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(username, null, roles);
    }
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException | NoSuchElementException e) {
            return false;
        }
    }
}
