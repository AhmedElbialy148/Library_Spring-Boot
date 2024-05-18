package com.maids.maidstask.auth.Jwt;

import com.maids.maidstask.patrons.models.Patron;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;
@Service
public class JwtService {
    // 256-bit key
    private final String SECRET_KEY = "43cdc434af3f2d70dda5818f89b0e01057d7516baa19d4f894b2a2dedf440c9e";

    public boolean isValid(String token, UserDetails user){
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
         Claims claims = extractAllClaims(token);
         return claimsResolver.apply(claims);
     }


    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String generateToken(Patron patron) {
        return Jwts.builder()
                .id(String.valueOf(patron.getId()))
                .subject(patron.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSignKey())
                .compact();
    }


    private SecretKey getSignKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
