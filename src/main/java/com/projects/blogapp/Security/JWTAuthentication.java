package com.projects.blogapp.Security;

import com.projects.blogapp.users.UserEntity;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication implements Authentication {
    String jwt;
    UserEntity userEntity;
    public JWTAuthentication(String jwt){
        this.jwt = jwt;
    }
    /**
     * @return
     */

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * @Return the Credential of the Authentication request
     * for eg:- password or Bearer or cookie
     */
    @Override
    public String getCredentials() {
        return jwt;
    }

    /**
     * @return
     */
    @Override
    public Object getDetails() {
        return null;
    }

    /**
     * @return
     * The principle is the entity being authenticated
     * in this case it's a user, other case client or third party
     */
    @Override
    public UserEntity getPrincipal() {
        return userEntity;
    }

    /**
     * @return
     */
    @Override
    public boolean isAuthenticated() {
        return userEntity != null;
    }

    /**
     * @param isAuthenticated
     * @throws IllegalArgumentException
     */
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    /**
     * @param another principal to compare with.
     * @return
     */
    @Override
    public boolean equals(Object another) {
        return false;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return null;
    }
}
