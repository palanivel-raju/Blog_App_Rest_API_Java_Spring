package com.projects.blogapp.Users;

import com.projects.blogapp.users.UserEntity;
import com.projects.blogapp.users.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UsersRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void can_create_user(){
        var user = UserEntity.builder()
                .username("sachin")
                .email("sachin@coolmail.com")
                        .build();
        userRepository.save(user);
    }
    @Test
    @Order(2)
    void can_find_user(){
        can_create_user();
        var user = userRepository.findAll();
        Assertions.assertEquals(1, user.size());
    }
}
