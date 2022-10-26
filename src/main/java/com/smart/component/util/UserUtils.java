package com.smart.component.util;

import com.smart.config.SetToStringConverter;
import com.smart.dto.EmployeeDto;
import com.smart.entity.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    private static String secret = null;

    public UserUtils(@Value("${jwt.secret}") String secret) {
        UserUtils.secret = secret;
    }

    public static EmployeeDto getCurrentUser(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build().parseClaimsJws(token)
                .getBody();
        EmployeeDto principal = new EmployeeDto();
        principal.setEmployeeId(Long.valueOf(String.valueOf(claims.get("userId"))));
        principal.setUsername(claims.getSubject());
        principal.setRoles(new SetToStringConverter().convertToEntityAttribute(claims.get("roles").toString()));
        return principal;
    }

//    public static EmployeeDto getCurrentUser() {
//        try {
//            return (EmployeeDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        } catch (Exception ignored) {
//            return null;
//        }
//    }
//
//    public static long getCurrentUserId() {
//        try {
//            return ((EmployeeDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployeeId();
//        } catch (Exception ignored) {
//            return 0;
//        }
//    }
}
