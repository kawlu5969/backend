package com.kawlul.demo.security.jwt;

import com.kawlul.demo.common.exception_handler.custom.SecurityErrorType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        try{
            jwtFilterLogic(request,response,filterChain);
        }catch (Exception e){
            filterChain.doFilter(request,response);
        }
    }

    private void jwtFilterLogic(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull FilterChain filterChain) throws ServletException, IOException{
        String authToken = getJwt(request);

        if(authToken==null){
            request.setAttribute("exception", SecurityErrorType.COOKIE_NOT_EXISTS);
            filterChain.doFilter(request,response);
            return;
        }

        final String jwt;
        final String userName;
        //jwt토큰을 안 보낸 경우 그냥 return
        if(!doesStartWithBearer(authToken)){
            request.setAttribute("exception", SecurityErrorType.JWT_NOT_EXISTS);
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authToken.substring(7);
        if(!jwtService.validateToken(jwt)){
            request.setAttribute("exception", SecurityErrorType.JWT_NOT_VALID);
            filterChain.doFilter(request, response);
            return;
        }

        userName = jwtService.extractUserName(jwt);
        if(StringUtils.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            setAuthentication(userDetails,request);
        }
        filterChain.doFilter(request,response);
    }

    private Boolean doesStartWithBearer(String authToken){
        if(StringUtils.startsWith(authToken,JwtUtils.BEARER)){
            return true;
        }
        return false;
    }

    private String getJwt(@NonNull HttpServletRequest request){
        String authToken = null;
        final Cookie[] cookies = request.getCookies();
        if(ObjectUtils.isEmpty(cookies)){
            request.setAttribute("exception", SecurityErrorType.COOKIE_NOT_EXISTS);
            return null;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                authToken = cookie.getValue();
            }
        }
        return authToken;
    }

    private void setAuthentication(UserDetails userDetails, @NonNull HttpServletRequest request){
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,null,userDetails.getAuthorities()
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}
