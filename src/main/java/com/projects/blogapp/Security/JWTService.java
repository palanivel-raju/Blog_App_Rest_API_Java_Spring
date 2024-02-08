package com.projects.blogapp.Security
        ;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JWTService {
    //ToDo: Move the key to the seperate .property config file will not add to git
    private static final String JWT_Key = "jhgsi5465dfdsgdf4gfsf4g5d4g4dfssddsdf";
    private Algorithm algorithm = Algorithm.HMAC256(JWT_Key);
    public String createJwt(Long userId){
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
               // .withExpiresAt()//TO Do: expire parameter
                .sign(algorithm);
    }
    public Long retriveUserId(String jwtString){
        var decodeJWT = JWT.decode(jwtString);
        var userId = Long.valueOf(decodeJWT.getSubject());
        return userId;
    }
}
