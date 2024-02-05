package com.projects.blogapp.users.DTO;

import lombok.*;

@Data
@Getter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String bio;
    private String image;
}
