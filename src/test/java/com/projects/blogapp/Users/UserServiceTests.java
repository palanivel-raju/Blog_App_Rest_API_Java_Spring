package com.projects.blogapp.Users;

import com.projects.blogapp.users.DTO.CreateUserRequest;
import com.projects.blogapp.users.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {
//    @Autowired
    UserService userService;
    @Test
    void can_Create_Users(){
       var user =  userService.create_user(new CreateUserRequest(
                "john",
                "Pass123",
                "john@blog.com"
        ));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("john", user.getUsername());
    }

}
