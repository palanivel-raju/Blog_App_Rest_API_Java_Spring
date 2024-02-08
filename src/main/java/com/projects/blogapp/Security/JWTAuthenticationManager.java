package com.projects.blogapp.Security;

import com.projects.blogapp.users.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationManager implements AuthenticationManager {
    private JWTService jwtService;
    private UserService userService;

    public JWTAuthenticationManager(JWTService jwtService, UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       try{
           var jwtAuthentication = (JWTAuthentication) authentication;
           var jwt = jwtAuthentication.getCredentials();
           var userId = jwtService.retriveUserId(jwt);
           var userEntity = userService.getUser(userId);
           jwtAuthentication.userEntity = userEntity;
           jwtAuthentication.setAuthenticated(true);
           return jwtAuthentication;
       }catch (Exception ex){
           throw new IllegalAccessError(ex.getMessage() + "  Cannot authenticate with non JWT authentication");
       }
    }
}
