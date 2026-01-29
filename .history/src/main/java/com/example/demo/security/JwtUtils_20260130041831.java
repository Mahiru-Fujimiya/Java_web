package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    // 🔥 KHÓA BÍ MẬT — bạn nên thay khóa dài hơn để an toàn!
    private static final String SECRET_KEY =
            "THIS_IS_YOUR_SECRET_KEY_CHANGE_IT_1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Thời gian sống token (1 ngày)
    private static final long EXPIRATION = 24 * 60 * 60 * 1000;

    // Tạo khóa ký
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // 👉 Tạo JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)                      // Lưu username trong token
                .setIssuedAt(new Date())                   // Ngày tạo
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) // Hết hạn
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)              // Ký token
                .compact();
    }

    // 👉 Lấy username từ token
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    // 👉 Kiểm tra token có hợp lệ không
    public boolean isValidToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 👉 Giải mã token → Claims
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // khóa
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
