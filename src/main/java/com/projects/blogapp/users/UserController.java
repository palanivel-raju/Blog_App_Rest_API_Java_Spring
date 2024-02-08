package com.projects.blogapp.users;

import com.projects.blogapp.Common.DTO.ErrorResponse;
import com.projects.blogapp.Security.JWTService;
import com.projects.blogapp.users.DTO.CreateUserRequest;
import com.projects.blogapp.users.DTO.LoginUserRequest;
import com.projects.blogapp.users.DTO.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;

    public UserController(UserService userService, ModelMapper modelMapper,
                          JWTService jwtService){
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }
    @PostMapping("")
    public ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request){
        UserEntity userEntity = userService.create_user(request);
        URI savedUserURI =URI.create("/users/"+ userEntity.getId());
        var UserResponse = modelMapper.map(userEntity, UserResponse.class);
        UserResponse.setToken(jwtService.createJwt(userEntity.getId()));
        return ResponseEntity.created(savedUserURI).body(UserResponse);
    }
    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request){
        UserEntity userEntity = userService.loginUser(request.getUsername(), request.getPassword());
        var UserResponse = modelMapper.map(userEntity, UserResponse.class);
        UserResponse.setToken(jwtService.createJwt(userEntity.getId()));
        return ResponseEntity.ok(UserResponse);
    }

    @ExceptionHandler({
            UserService.UserNotFoundException.class
    })
    ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception ex){
        String message;
        HttpStatus status;
        if(ex instanceof UserService.UserNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }
        else{
            message =" Something went Wrong";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponse response = ErrorResponse.builder().message(message).build();
        return ResponseEntity.status(status).body(response);
    }
}
