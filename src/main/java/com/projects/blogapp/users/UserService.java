package com.projects.blogapp.users;

import com.projects.blogapp.users.DTO.CreateUserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity create_user(CreateUserRequest u){
        var userEntity = UserEntity.builder()
                .username(u.getUsername())
                .email(u.getEmail())//To password has to provide
                .build();
        return userRepository.save(userEntity);
    }
    public UserEntity getUser(long userId){
        Optional<UserEntity> user = userRepository.findByUserId(userId);
        return user.orElseThrow(()-> new UserNotFoundException(userId));
    }
    public UserEntity getUser(String name){
       return userRepository.findByUserName(name).orElseThrow(()-> new UserNotFoundException(name));

    }

    public UserEntity loginUser(String name, String Password){
        var user =  userRepository.findByUserName(name).orElseThrow(()-> new UserNotFoundException(name));
        //other way of to do
//        Optional<UserEntity> user = userRepository.findByUserName(name);
//        if(user.isEmpty()){
//            throw new UserNotFoundException(name);
//        }
        // To do: match the password

        return user;
    }
    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String s){
            super(s+" username is not found, retry");
        }
        public UserNotFoundException(long userId){
            super(userId+" userId is not found, retry");
        }
    }
}
