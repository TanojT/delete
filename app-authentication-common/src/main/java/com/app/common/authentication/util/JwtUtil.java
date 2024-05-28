package com.app.common.authentication.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

    private final String SECRET_KEY = "1n2-3t4r5a6d7e8r9s";
    private final String AUTHORITIES = "authorities";
    private final Integer TOKEN_EXPIRATION_DURATION = 1000 * 60 * 10; // 10 mins

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public List<String> getAuthorities(String token) {
        return extractClaim(token, claims -> {
            List<String> authorities = claims.get(this.AUTHORITIES, List.class);
            return authorities;
        });
    }

    public List<GrantedAuthority> extractGrantedAuthorities(String token) {
        return getGrantedAuthorities(getAuthorities(token));
    }

    public List<GrantedAuthority> getGrantedAuthorities(List<String> authorities) {
 
        return authorities.stream().map(authorityStr -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(authorityStr);
            return authority;
        }).collect(Collectors.toList());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(List<GrantedAuthority> roles, String username) {
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put(AUTHORITIES, roles);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.TOKEN_EXPIRATION_DURATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, String username) {
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }
}
