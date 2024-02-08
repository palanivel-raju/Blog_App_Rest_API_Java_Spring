package com.projects.blogapp.Security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JWTServiceTests {
    JWTService jwtService = new JWTService();
    @Test
    void canCreateJWTFromUserId(){
       var jwt = jwtService.createJwt(1000L);

       assertNotNull(jwt);
    }

}
