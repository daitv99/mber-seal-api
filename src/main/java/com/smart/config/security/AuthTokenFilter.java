//package com.smart.config.security;
//
//import com.smart.component.util.UserUtils;
//import com.smart.dto.EmployeeDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class AuthTokenFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String jwtToken = parseJwt(request);
//        if (jwtToken != null) {
//            EmployeeDto authInfo = UserUtils.getCurrentUser(jwtToken);
//            Set<SimpleGrantedAuthority> authorities = authInfo.getRoles().stream().map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toSet());
//            PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken =
//                    new PreAuthenticatedAuthenticationToken(authInfo, null, authorities);
//            SecurityContextHolder.getContext().setAuthentication(preAuthenticatedAuthenticationToken);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String parseJwt(HttpServletRequest request) {
//        String headerAuth = request.getHeader("Authorization");
//
//        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//            return headerAuth.substring(7);
//        } else {
//            return headerAuth;
//        }
//    }
//}
